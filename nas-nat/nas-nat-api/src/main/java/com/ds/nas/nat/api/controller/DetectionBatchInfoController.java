package com.ds.nas.nat.api.controller;

import com.ds.nas.nat.common.result.Result;
import com.ds.nas.nat.dao.request.DetectionBatchInfoCreateRequest;
import com.ds.nas.nat.dao.request.DetectionBatchInfoDetectionRequest;
import com.ds.nas.nat.dao.request.DetectionBatchInfoSubmitRequest;
import com.ds.nas.nat.service.NatDetectionBatchInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2022/12/11
 * @description 检测批次
 */
@RestController
@RequestMapping("detection-batch")
public class DetectionBatchInfoController {

    @Resource
    private NatDetectionBatchInfoService detectionBatchInfoService;

    @PostMapping("create")
    public Result<String> create(@RequestBody DetectionBatchInfoCreateRequest request) {
        return detectionBatchInfoService.create(request);
    }

    @PostMapping("submit")
    public Result<String> submit(@RequestBody DetectionBatchInfoSubmitRequest request) {
        return detectionBatchInfoService.submit(request);
    }

    @PostMapping("detection")
    public Result<String> detection(@RequestBody DetectionBatchInfoDetectionRequest request) {
        return detectionBatchInfoService.detection(request);
    }

}
