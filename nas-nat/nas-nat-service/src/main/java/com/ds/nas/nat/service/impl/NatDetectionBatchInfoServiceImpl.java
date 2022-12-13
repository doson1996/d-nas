package com.ds.nas.nat.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.nas.nat.common.result.Result;
import com.ds.nas.nat.dao.domain.NatDetectionBatchInfo;
import com.ds.nas.nat.dao.request.DetectionBatchInfoCreateRequest;
import com.ds.nas.nat.dao.request.DetectionBatchInfoDetectionRequest;
import com.ds.nas.nat.dao.request.DetectionBatchInfoSubmitRequest;
import com.ds.nas.nat.service.NatDetectionBatchInfoService;
import com.ds.nas.nat.dao.mapper.NatDetectionBatchInfoMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author ds
 * @description 针对表【nat_detection_batch_info】的数据库操作Service实现
 * @createDate 2022-12-10 23:55:13
 */
@Service
public class NatDetectionBatchInfoServiceImpl extends ServiceImpl<NatDetectionBatchInfoMapper, NatDetectionBatchInfo>
        implements NatDetectionBatchInfoService {

    @Override
    public Result<String> create(DetectionBatchInfoCreateRequest request) {
        NatDetectionBatchInfo detectionBatchInfo = new NatDetectionBatchInfo();
        detectionBatchInfo.setBatchNo(request.getBatchNo());
        if (save(detectionBatchInfo)) {
            return Result.ok("创建批次[" + request.getBatchNo() + "]成功!", request.getBatchNo());
        }
        return Result.fail("创建批次[" + request.getBatchNo() + "]失败!");
    }

    @Override
    public Result<String> submit(DetectionBatchInfoSubmitRequest request) {
        NatDetectionBatchInfo detectionBatchInfo = new NatDetectionBatchInfo();
        detectionBatchInfo.setBatchNo(request.getBatchNo());
        detectionBatchInfo.setEntryTime(new Date());
        detectionBatchInfo.setEntryMechanism(request.getEntryMechanism());
        detectionBatchInfo.setType(request.getType());

        LambdaUpdateWrapper<NatDetectionBatchInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(NatDetectionBatchInfo::getBatchNo, request.getBatchNo());
        if (update(detectionBatchInfo, wrapper)) {
            return Result.ok("提交批次[" + request.getBatchNo() + "]成功!", request.getBatchNo());
        }
        return Result.fail("提交批次[" + request.getBatchNo() + "]失败!");
    }

    @Override
    public Result<String> detection(DetectionBatchInfoDetectionRequest request) {
        NatDetectionBatchInfo detectionBatchInfo = new NatDetectionBatchInfo();
        detectionBatchInfo.setBatchNo(request.getBatchNo());
        detectionBatchInfo.setDetectionTime(new Date());
        detectionBatchInfo.setDetectionResult(1);
        detectionBatchInfo.setDetectionMechanism(request.getDetectionMechanism());

        LambdaUpdateWrapper<NatDetectionBatchInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(NatDetectionBatchInfo::getBatchNo, request.getBatchNo());
        if (update(detectionBatchInfo, wrapper)) {
            return Result.ok("检测批次[" + request.getBatchNo() + "]成功!", request.getBatchNo());
        }
        return Result.fail("检测批次[" + request.getBatchNo() + "]失败!");
    }

}




