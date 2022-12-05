package com.ds.nas.hc.api.controller;

import com.ds.nas.hc.common.result.Result;
import com.ds.nas.hc.dao.request.HealthCodeApplyRequest;
import com.ds.nas.hc.dao.response.ApplyHealthCodeResponse;
import com.ds.nas.hc.service.HcPersonalInfoService;
import org.springframework.web.bind.annotation.*;

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
    public Result<ApplyHealthCodeResponse> apply(@RequestBody HealthCodeApplyRequest request) {

        return hcPersonalInfoService.apply(request);
    }

    /**
     * 查询健康码
     * //todo 可以单独拆分成服务部署
     *
     * @param idCard
     * @return
     */
    @GetMapping("query/{idCard}")
    public Result<ApplyHealthCodeResponse> query(@PathVariable String idCard) {
        return hcPersonalInfoService.queryByIdCard(idCard);
    }

}
