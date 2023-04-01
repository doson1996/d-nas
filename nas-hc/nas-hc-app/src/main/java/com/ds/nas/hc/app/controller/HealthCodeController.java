package com.ds.nas.hc.app.controller;

import com.ds.nas.hc.dao.request.HealthCodeApplyRequest;
import com.ds.nas.hc.dao.request.HealthCodeQueryRequest;
import com.ds.nas.hc.dao.response.HealthCodeQueryResponse;
import com.ds.nas.hc.service.HcPersonalInfoService;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2022/12/4
 * @description health-code
 */
@Api("health-code")
@RestController
@RequestMapping("health-code")
public class HealthCodeController {

    @Resource
    private HcPersonalInfoService hcPersonalInfoService;

    /**
     * 申领(废弃)
     *
     * @param request 入参
     * @return 出参
     */

    @Deprecated
    @GetMapping("apply")
    public Result<StringResponse> apply(@RequestBody HealthCodeApplyRequest request) {
        return Result.fail("此方法已废弃");
    }

    /**
     * 查询
     * //todo 可以单独拆分成服务部署
     *
     * @param request 入参
     * @return 出参
     */
    @ApiOperation("查询")
    @PostMapping("query")
    public Result<HealthCodeQueryResponse> query(@RequestBody HealthCodeQueryRequest request) {
        return hcPersonalInfoService.queryByIdCard(request);
    }

}
