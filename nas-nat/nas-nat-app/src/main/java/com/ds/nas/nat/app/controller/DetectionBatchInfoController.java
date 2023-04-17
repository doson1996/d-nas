package com.ds.nas.nat.app.controller;

import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.nat.api.io.request.DetectionBatchInfoCreateRequest;
import com.ds.nas.nat.api.io.request.DetectionBatchInfoDetectionRequest;
import com.ds.nas.nat.api.io.request.DetectionBatchInfoSubmitRequest;
import com.ds.nas.nat.service.NatDetectionBatchInfoService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("detection-batch")
public class DetectionBatchInfoController {

    @Resource
    private NatDetectionBatchInfoService detectionBatchInfoService;

    @PostMapping("getBatchNo")
    public Result<StringResponse> getBatchNo() {
        return detectionBatchInfoService.getBatchNo();
    }

    @PostMapping("create")
    public Result<StringResponse> create(@RequestBody DetectionBatchInfoCreateRequest request) {
        return detectionBatchInfoService.create(request);
    }

    @PostMapping("submit")
    public Result<StringResponse> submit(@RequestBody DetectionBatchInfoSubmitRequest request) {
        return detectionBatchInfoService.submit(request);
    }

    @PostMapping("detection")
    public Result<StringResponse> detection(@RequestBody DetectionBatchInfoDetectionRequest request) {
        return detectionBatchInfoService.detection(request);
    }

}
