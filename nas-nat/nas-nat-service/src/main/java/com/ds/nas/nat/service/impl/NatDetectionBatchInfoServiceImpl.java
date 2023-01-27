package com.ds.nas.nat.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.lib.cache.redis.RedisUtil;
import com.ds.nas.hc.api.dubbo.PersonalInfoProvider;
import com.ds.nas.hc.dao.request.PersonalInfoBatchUpdateRequest;
import com.ds.nas.hc.dao.response.PersonalInfoBatchUpdateResponse;
import com.ds.nas.lib.common.base.db.DBUtils;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.exception.BusinessException;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.lib.common.util.StringUtils;
import com.ds.nas.nat.common.constant.CacheKey;
import com.ds.nas.nat.common.util.TableNameUtils;
import com.ds.nas.nat.dao.domain.NatDetectionBatchInfo;
import com.ds.nas.nat.dao.mapper.NatDetectionBatchInfoMapper;
import com.ds.nas.nat.dao.mapper.NatDetectionPersonalInfoMapper;
import com.ds.nas.nat.dao.request.DetectionBatchInfoCreateRequest;
import com.ds.nas.nat.dao.request.DetectionBatchInfoDetectionRequest;
import com.ds.nas.nat.dao.request.DetectionBatchInfoSubmitRequest;
import com.ds.nas.nat.service.NatDetectionBatchInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ds
 * @description 针对表【nat_detection_batch_info】的数据库操作Service实现
 * @createDate 2022-12-10 23:55:13
 */
@Slf4j
@Service
public class NatDetectionBatchInfoServiceImpl extends ServiceImpl<NatDetectionBatchInfoMapper, NatDetectionBatchInfo>
        implements NatDetectionBatchInfoService {

    @Resource
    RedisUtil redisUtil;

    @Value("${table-name.dpi}")
    private String dpiTableName;

    @DubboReference(version = "1.0")
    private PersonalInfoProvider personalInfoProvider;

    @Resource
    private NatDetectionPersonalInfoMapper detectionPersonalInfoMapper;

    /**
     * 每日批次起始值
     */
    private static final String BATCH_SEQUENCE_MIN = "0000000000";

    @Override
    public Result<StringResponse> getBatchNo() {
        String batchNo = generateBatchNo();
        return Result.ok("获取批次号[" + batchNo + "]成功!",
                StringResponse.builder().withData(batchNo).build());
    }

    @Override
    public Result<StringResponse> create(DetectionBatchInfoCreateRequest request) {
        NatDetectionBatchInfo detectionBatchInfo = new NatDetectionBatchInfo();
        String batchNo = generateBatchNo();
        detectionBatchInfo.setBatchNo(batchNo);
        DBUtils.getCurrentDBUtils().onCreate(detectionBatchInfo);

        if (StringUtils.isNotBlank(batchNo) && save(detectionBatchInfo)) {
            return Result.ok("创建批次[" + batchNo + "]成功!",
                    StringResponse.builder().withData(batchNo).build());
        }
        return Result.fail("创建批次[" + batchNo + "]失败!",
                StringResponse.builder().withData(batchNo).build());
    }

    @Override
    public Result<StringResponse> submit(DetectionBatchInfoSubmitRequest request) {
        NatDetectionBatchInfo detectionBatchInfo = new NatDetectionBatchInfo();
        detectionBatchInfo.setBatchNo(request.getBatchNo());
        detectionBatchInfo.setEntryTime(new Date());
        detectionBatchInfo.setEntryMechanism(request.getEntryMechanism());
        detectionBatchInfo.setType(request.getType());

        LambdaUpdateWrapper<NatDetectionBatchInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(NatDetectionBatchInfo::getBatchNo, request.getBatchNo());
        if (update(detectionBatchInfo, wrapper)) {
            return Result.ok("提交批次[" + request.getBatchNo() + "]成功!",
                    StringResponse.builder().withData(request.getBatchNo()).build());
        }
        return Result.fail("提交批次[" + request.getBatchNo() + "]失败!",
                StringResponse.builder().withData(request.getBatchNo()).build());
    }

    @Override
    public Result<StringResponse> detection(DetectionBatchInfoDetectionRequest request) {
        NatDetectionBatchInfo detectionBatchInfo = new NatDetectionBatchInfo();
        detectionBatchInfo.setBatchNo(request.getBatchNo());
        detectionBatchInfo.setDetectionTime(new Date());
        detectionBatchInfo.setDetectionResult(request.getDetectionResult());
        detectionBatchInfo.setDetectionMechanism(request.getDetectionMechanism());

        LambdaUpdateWrapper<NatDetectionBatchInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(NatDetectionBatchInfo::getBatchNo, request.getBatchNo());
        if (update(detectionBatchInfo, wrapper)) {
            updateHealthCode(request.getBatchNo(), request.getDetectionResult());
            return Result.ok("检测批次[" + request.getBatchNo() + "]成功!",
                    StringResponse.builder().withData(request.getBatchNo()).build());
        }
        return Result.fail("检测批次[" + request.getBatchNo() + "]失败!",
                StringResponse.builder().withData(request.getBatchNo()).build());
    }

    /**
     * 根据批次号更新 todo 改消息队列
     *
     * @param batchNo
     */
    private void updateHealthCode(String batchNo, Integer health) {
        try {
            String tableName = TableNameUtils.getByBatchNo(dpiTableName, batchNo);
            List<String> idCards = detectionPersonalInfoMapper.getIdCards(tableName, batchNo);
            PersonalInfoBatchUpdateRequest request = new PersonalInfoBatchUpdateRequest();
            request.setHealth(health);
            request.setLastNucleicAcidTime(new Date());
            request.setIdCards(idCards);
            Result<PersonalInfoBatchUpdateResponse> result = personalInfoProvider.updateByIdCards(request);
            log.info("updateHealthCode result = {}", result);
        } catch (Exception e) {
            log.error("根据批次号更新异常: {}", e.getMessage());
        }
    }

    /**
     * 生成批次号
     *
     * @return 批次号
     */
    private String generateBatchNo() {
        String batchNo = "";
        String today = DateUtil.today().replaceAll("-", "");
        String key = CacheKey.BATCH_SEQUENCE_KEY + today;
        try {
            String sequence = redisUtil.get(key);
            if (StringUtils.isBlank(sequence)) {
                // 初始化序列号
                String initSequence = today + BATCH_SEQUENCE_MIN;
                redisUtil.set(key, initSequence, 2 * DateUnit.DAY.getMillis());
            }
            sequence = String.valueOf(redisUtil.incrBy(key, 1));
            if (!today.equals(sequence.substring(0, today.length()))) {
                throw new BusinessException("当日批次号已用完!");
            }
            batchNo = "cd" + sequence;
        } catch (Exception e) {
            log.error("生成批次号异常：{}", e.getMessage());
            // 抛出异常给全局异常处理
            throw e;
        }
        return batchNo;
    }

}




