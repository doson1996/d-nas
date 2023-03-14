package com.ds.nas.nat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.nas.lib.common.base.db.DBUtils;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.nat.common.util.TableNameUtils;
import com.ds.nas.nat.dao.domain.NatDetectionPersonalInfo;
import com.ds.nas.nat.dao.mapper.NatDetectionPersonalInfoMapper;
import com.ds.nas.nat.dao.request.DetectionPersonalInfoEntryRequest;
import com.ds.nas.nat.service.NatDetectionPersonalInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import javax.annotation.Resource;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author ds
 * @description 针对表【nat_detection_personal_info】的数据库操作Service实现
 * @createDate 2022-12-10 23:58:31
 */
@Service
public class NatDetectionPersonalInfoServiceImpl extends ServiceImpl<NatDetectionPersonalInfoMapper, NatDetectionPersonalInfo>
        implements NatDetectionPersonalInfoService {

    @Value("${table-name.dpi}")
    private String dpiTableName;

    @Resource
    private DataSourceTransactionManager transactionManager;

    @Resource
    private TransactionDefinition transactionDefinition;

    @Resource
    private NatDetectionPersonalInfoMapper personalInfoMapper;

    /**
     * 使用for update需要开启事务（可以避免重复录入,性能下降）
     * 事务  请求次数   平均值(ms)  中位数    90%      95%     99%    最小值   最大值    吞吐量
     * 无     500       232	    226 	290	    342	    522 	35  	755     168/s
     * 注解   500	    1990	    1973	2435	2566	2615	325	    2640    24/s
     * 编程   500	    1933	    2026	2058	2063	2073	738	    3370    24/s
     *
     * @param request 入参
     * @return
     */
    // @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<StringResponse> entry(DetectionPersonalInfoEntryRequest request) {
        NatDetectionPersonalInfo personalInfo = new NatDetectionPersonalInfo();
        personalInfo.setBatchNo(request.getBatchNo());
        personalInfo.setIdCard(request.getIdCard());
        String tableName = getTableName(request.getBatchNo());
//        // 开启事务（获取事务）
//        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
//        int countByBatchNo = personalInfoMapper.countByBatchNo(tableName, request.getBatchNo(), request.getIdCard());
//        // 提交事务
//        transactionManager.commit(transactionStatus);
//        // 回滚事务
//        // transactionManager.rollback(transactionStatus);
//        if (countByBatchNo > 0) {
//            return Result.fail("请勿重复录入!");
//        }
        DBUtils.onCreate(personalInfo);
        int res = 0;
        try {
            res = personalInfoMapper.entry(tableName, personalInfo);
        } catch (Exception e) {
            // 使用 批次号 + 身份证号唯一索引来控制重复录入
            if (e.getCause().getMessage().contains("uk_batch_no_id_card"))
                return Result.fail("请勿重复录入!");
        }
        if (res == 0) {
            return Result.fail("录入信息失败!");
        }
        return Result.ok("录入信息成功!",
                StringResponse.builder().withData(request.getIdCard()).build());
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




