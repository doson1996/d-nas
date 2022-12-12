package com.ds.nas.nat.api.controller;

import com.ds.nas.nat.common.result.Result;
import com.ds.nas.nat.dao.request.DetectionPersonalInfoRequest;
import com.ds.nas.nat.service.NatDetectionPersonalInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2022/12/11
 * @description
 */
@RestController
@RequestMapping("detection-personal")
public class DetectionPersonalInfoController {

    @Resource
    private NatDetectionPersonalInfoService detectionPersonalInfoService;

    @PostMapping("detection")
    public Result<String> detection(@RequestBody DetectionPersonalInfoRequest request) {
        return detectionPersonalInfoService.detection(request);
    }

}
