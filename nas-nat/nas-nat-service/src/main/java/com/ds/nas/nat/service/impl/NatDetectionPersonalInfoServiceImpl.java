package com.ds.nas.nat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.nas.nat.common.result.Result;
import com.ds.nas.nat.common.util.TableNameUtil;
import com.ds.nas.nat.dao.domain.NatDetectionPersonalInfo;
import com.ds.nas.nat.dao.mapper.NatDetectionBatchInfoMapper;
import com.ds.nas.nat.dao.request.DetectionPersonalInfoRequest;
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

    @Value("${table-name.dpi}")
    private String dpiTableName;

    @Resource
    private NatDetectionPersonalInfoMapper personalInfoMapper;

    @Resource
    private NatDetectionBatchInfoMapper batchInfoMapper;

    @Override
    public Result<String> detection(DetectionPersonalInfoRequest request) {
        NatDetectionPersonalInfo personalInfo = new NatDetectionPersonalInfo();
        personalInfo.setBatchNo(request.getBatchNo());
        personalInfo.setIdCard(request.getIdCard());
        String tableName = TableNameUtil.generateTodayTableName(dpiTableName);
        int res = personalInfoMapper.detection(tableName, personalInfo);
        return null;
    }
}




