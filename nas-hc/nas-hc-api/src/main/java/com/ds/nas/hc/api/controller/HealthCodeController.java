package com.ds.nas.hc.api.controller;

import com.ds.nas.hc.common.result.Result;
import com.ds.nas.hc.dao.request.ApplyHealthCodeRequest;
import com.ds.nas.hc.dao.response.ApplyHealthCodeResponse;
import com.ds.nas.hc.service.HcPersonalInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2022/12/4
 * @description 健康码
 */
@RestController
@RequestMapping("health-code")
public class HealthCodeController {

    @Resource
    private HcPersonalInfoService hcPersonalInfoService;

    /**
     * 申领健康码
     *
     * @return
     */
    @PostMapping("apply")
    public Result<ApplyHealthCodeResponse> apply(@RequestBody ApplyHealthCodeRequest request) {

        return hcPersonalInfoService.apply(request);
    }


}
