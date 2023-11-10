package com.ds.nas.nat.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.nas.lib.common.base.annotation.CheckParam;
import com.ds.nas.lib.common.base.db.DBUtils;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.entity.RecentNucleicAcid;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.nat.api.io.request.DetectionPersonalInfoEntryRequest;
import com.ds.nas.nat.api.io.request.RecentNucleicAcidRecordsQueryRequest;
import com.ds.nas.nat.common.util.TableNameUtils;
import com.ds.nas.nat.dao.domain.NatDetectionPersonalInfo;
import com.ds.nas.nat.dao.mapper.NatDetectionPersonalInfoMapper;
import com.ds.nas.nat.service.NatDetectionPersonalInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author ds
 * @description 针对表【nat_detection_personal_info】的数据库操作Service实现
 * @createDate 2022-12-10 23:58:31
 */
@Slf4j
@Service
public class NatDetectionPersonalInfoServiceImpl extends ServiceImpl<NatDetectionPersonalInfoMapper, NatDetectionPersonalInfo>
        implements NatDetectionPersonalInfoService {

    @Value("${table-name.dpi : nat_detection_personal_info}")
    private String dpiTableName;

    @Resource
    private NatDetectionPersonalInfoMapper personalInfoMapper;

    /**
     * 使用for update需要开启事务（可以避免重复录入,性能较低）
     * 事务  请求次数   平均值(ms)  中位数    90%      95%     99%    最小值   最大值    吞吐量
     * 无     500        232	    226 	290	    342	    522 	35  	755     168/s
     * 注解   500	    1990	    1973	2435	2566	2615	325	    2640    24/s
     * 编程   500	    1933	    2026	2058	2063	2073	738	    3370    24/s
     *
     * @param request 入参
     * @return
     */
    // @Transactional(rollbackFor = Exception.class)
    @CheckParam
    @Override
    public Result<StringResponse> entry(DetectionPersonalInfoEntryRequest request) {
        NatDetectionPersonalInfo personalInfo = new NatDetectionPersonalInfo();
        personalInfo.setBatchNo(request.getBatchNo());
        personalInfo.setIdCard(request.getIdCard());
        String tableName = getTableName(request.getBatchNo());
        DBUtils.onCreate(personalInfo);
        int res;
        try {
            res = personalInfoMapper.entry(tableName, personalInfo);
        } catch (Exception e) {
            // 使用 批次号 + 身份证号唯一索引来控制重复录入
            if (DuplicateKeyException.class.isAssignableFrom(e.getClass())
                    && e.getCause().getMessage().contains("uk_batch_no_id_card"))
                return Result.fail("请勿重复录入[身份证号:" + request.getIdCard() + "]!");
            // 其它异常继续往外抛
            log.info("{}.entry发生异常,data: {}  ex: {}",
                    this.getClass().getSimpleName(), request, e.getMessage());
            throw e;
        }
        if (res == 0) {
            return Result.fail("录入信息失败!");
        }
        return Result.ok("录入信息成功!",
                StringResponse.builder().withData(request.getIdCard()).build());
    }

    @CheckParam
    @Override
    public Result<List<RecentNucleicAcid>> recentNucleicAcidRecordsQuery(RecentNucleicAcidRecordsQueryRequest request) {
        List<RecentNucleicAcid> recentNucleicAcids = new ArrayList<>();
        String idCard = request.getIdCard();
        Integer days = request.getDays();
        Set<String> tableNames = TableNameUtils.getPreDaysTableName(dpiTableName, days);
        for (String tableName : tableNames) {
            // NatDetectionPersonalInfo natDetectionPersonalInfo;
            try {
              //  natDetectionPersonalInfo = personalInfoMapper.selectByIdCard(tableName, idCard);
            } catch (Exception e) {
                log.error("NatDetectionPersonalInfoServiceImpl.recentNucleicAcidRecordsQuery ex:", e);
                continue;
            }
            RecentNucleicAcid recentNucleicAcid = new RecentNucleicAcid();
           // BeanUtil.copyProperties(natDetectionPersonalInfo, recentNucleicAcid);
            recentNucleicAcids.add(recentNucleicAcid);
        }


        /**
         * SELECT t2.detection_mechanism,t2.detection_time,t2.detection_result from (
         * SELECT * FROM nat_detection_personal_info_20230313 WHERE id_card = '231124193809292241'
         * UNION ALL
         * SELECT * FROM nat_detection_personal_info_20230314 WHERE id_card = '231124193809292241'
         * UNION ALL
         * SELECT * FROM nat_detection_personal_info_20230328 WHERE id_card = '231124193809292241'
         * ) t1
         *  LEFT JOIN nat_detection_batch_info t2
         * 	on t1.batch_no = t2.batch_no
         */
        return null;
    }

    /**
     * 获取表名
     *
     * @param batchNo 批次号
     * @return 表名
     */
    private String getTableName(String batchNo) {
        return TableNameUtils.getByBatchNo(dpiTableName, batchNo);
    }

}




