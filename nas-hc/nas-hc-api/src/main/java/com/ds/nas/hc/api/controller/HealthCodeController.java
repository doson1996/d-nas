package com.ds.nas.hc.api.controller;

import com.ds.nas.hc.common.result.Result;
import com.ds.nas.hc.dao.request.HealthCodeApplyRequest;
import com.ds.nas.hc.dao.response.HealthCodeQueryResponse;
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
     * @param request 入参
     * @return 出参
     */
    @PostMapping("apply")
    public Result<String> apply(@RequestBody HealthCodeApplyRequest request) {
        return hcPersonalInfoService.apply(request);
    }

    /**
     * 查询健康码
     * //todo 可以单独拆分成服务部署
     *
     * @param idCard 身份证号
     * @return 出参
     */
    @GetMapping("query/{idCard}")
    public Result<HealthCodeQueryResponse> query(@PathVariable String idCard) {
        return hcPersonalInfoService.queryByIdCard(idCard);
    }

}
