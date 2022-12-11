package com.ds.nas.nat.api.controller;

import com.ds.nas.nat.common.result.Result;
import com.ds.nas.nat.service.NatDetectionPersonalInfoService;
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
    private NatDetectionPersonalInfoService detectionPersonalInfoService;

    public Result<String> detection() {
        return Result.ok();
    }

}
