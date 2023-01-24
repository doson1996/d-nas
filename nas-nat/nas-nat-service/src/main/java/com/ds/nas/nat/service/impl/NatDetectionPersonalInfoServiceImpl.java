package com.ds.nas.nat.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.lib.cache.redis.RedisUtil;
import com.ds.nas.lib.common.base.db.DBUtils;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.lib.common.util.StringUtils;
import com.ds.nas.nat.common.util.TableNameUtils;
import com.ds.nas.nat.dao.domain.NatDetectionPersonalInfo;
import com.ds.nas.nat.dao.request.DetectionPersonalInfoEntryRequest;
import com.ds.nas.nat.service.NatDetectionPersonalInfoService;
import com.ds.nas.nat.dao.mapper.NatDetectionPersonalInfoMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ds
 * @description 针对表【nat_detection_personal_info】的数据库操作Service实现
 * @createDate 2022-12-10 23:58:31
 */
@Service
public class NatDetectionPersonalInfoServiceImpl extends ServiceImpl<NatDetectionPersonalInfoMapper, NatDetectionPersonalInfo>
        implements NatDetectionPersonalInfoService {

    @Resource
    private RedisUtil redisUtil;

    @Value("${table-name.dpi}")
    private String dpiTableName;

    @Resource
    private NatDetectionPersonalInfoMapper personalInfoMapper;

    @Override
    public Result<StringResponse> entry(DetectionPersonalInfoEntryRequest request) {
        NatDetectionPersonalInfo personalInfo = new NatDetectionPersonalInfo();
        personalInfo.setBatchNo(request.getBatchNo());
        personalInfo.setIdCard(request.getIdCard());
        String tableName = getTableName(request.getBatchNo());
        DBUtils.getCurrentDBUtils().onCreate(personalInfo);
        int res = personalInfoMapper.entry(tableName, personalInfo);
        if (res == 0) {
            return Result.fail("录入信息失败!");
        }
        return Result.ok("录入信息成功!",
                StringResponse.builder().withData(request.getIdCard()).build());
    }

    /**
     * 获取批次表名
     * @param batchNo
     * @return
     */
    private String getTableName(String batchNo) {
        String key = TableNameUtils.BATCH_TABLE_KEY + batchNo;
        String tableName = redisUtil.get(key);
        if (StringUtils.isBlank(tableName)) {
            tableName = TableNameUtils.generateTodayTableName(dpiTableName);
            redisUtil.set(key, tableName);
        }
        return tableName;
    }

}




