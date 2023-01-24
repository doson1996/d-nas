package com.ds.nas.nat.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.lib.cache.redis.RedisUtil;
import com.ds.nas.hc.api.fegin.PersonalInfoClient;
import com.ds.nas.hc.dao.request.PersonalInfoUpdateRequest;
import com.ds.nas.hc.dao.response.PersonalInfoUpdateResponse;
import com.ds.nas.lib.common.base.db.DBUtils;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.lib.common.util.StringUtils;
import com.ds.nas.nat.common.util.TableNameUtils;
import com.ds.nas.nat.dao.domain.NatDetectionBatchInfo;
import com.ds.nas.nat.dao.mapper.NatDetectionBatchInfoMapper;
import com.ds.nas.nat.dao.mapper.NatDetectionPersonalInfoMapper;
import com.ds.nas.nat.dao.request.DetectionBatchInfoCreateRequest;
import com.ds.nas.nat.dao.request.DetectionBatchInfoDetectionRequest;
import com.ds.nas.nat.dao.request.DetectionBatchInfoSubmitRequest;
import com.ds.nas.nat.service.NatDetectionBatchInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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

    @Resource
    private PersonalInfoClient personalInfoClient;

    @Resource
    private NatDetectionPersonalInfoMapper detectionPersonalInfoMapper;

    /**
     * 批量序号redis key
     */
    private static final String BATCH_SEQUENCE_KEY = "batch:sequence:";

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
            updateHealthCode(request.getBatchNo());
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
    private void updateHealthCode(String batchNo) {
        PersonalInfoUpdateRequest request = new PersonalInfoUpdateRequest();
        detectionPersonalInfoMapper.getIdCards(TableNameUtils.generateTodayTableName(dpiTableName), batchNo);
        Result<PersonalInfoUpdateResponse> personalInfoUpdateResponseResult = personalInfoClient.updateByIdCard(request);
        log.info("personalInfoUpdateResponseResult = {}", personalInfoUpdateResponseResult);
    }

    /**
     * 生成批次号
     *
     * @return 批次号
     */
    private String generateBatchNo() {
        String batchNo = "";
        String today = DateUtil.today().replaceAll("-", "");
        String key = BATCH_SEQUENCE_KEY + today;
        try {
            String sequence = redisUtil.get(key);
            if (StringUtils.isBlank(sequence)) {
                // 初始化序列号
                String initSequence = today + "0000000000";
                redisUtil.set(key, initSequence);
            }
            sequence = String.valueOf(redisUtil.incrBy(key, 1));
            batchNo = "cd" + sequence;
        } catch (Exception e) {
            log.error("生成批次号异常：{}", e.getMessage());
        }
        return batchNo;
    }

}




