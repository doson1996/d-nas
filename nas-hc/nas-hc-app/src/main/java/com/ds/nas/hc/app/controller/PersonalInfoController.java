package com.ds.nas.hc.app.controller;

import com.ds.nas.hc.api.io.request.PersonalInfoBatchUpdateRequest;
import com.ds.nas.hc.api.io.response.PersonalInfoBatchUpdateResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.hc.api.io.request.PersonalInfoRegisterRequest;
import com.ds.nas.hc.api.io.request.PersonalInfoUpdateRequest;
import com.ds.nas.hc.api.io.response.PersonalInfoRegisterResponse;
import com.ds.nas.hc.api.io.response.PersonalInfoUpdateResponse;
import com.ds.nas.hc.service.HcPersonalInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2022/12/4
 * @description
 */
@RestController
@RequestMapping("personal-info")
public class PersonalInfoController {

    @Resource
    private HcPersonalInfoService hcPersonalInfoService;

    /**
     * 注册
     * @param request 入参
     * @return
     */
    @ApiOperation("注册")
    @PostMapping("register")
    public Result<PersonalInfoRegisterResponse> register(@RequestBody PersonalInfoRegisterRequest request) {
        return hcPersonalInfoService.register(request);
    }

    /**
     * 根据身份证号更新
     * @param request 入参
     * @return
     */
    @ApiOperation("根据身份证号更新")
    @PostMapping("updateByIdCard")
    public Result<PersonalInfoUpdateResponse> updateByIdCard(@RequestBody PersonalInfoUpdateRequest request) {
        return hcPersonalInfoService.updateByIdCard(request);
    }

    /**
     * 根据身份证号批量更新
     * @param request 入参
     * @return
     */
    @ApiOperation("根据身份证号批量更新")
    @PostMapping("updateByIdCards")
    public Result<PersonalInfoBatchUpdateResponse> updateByIdCards(@RequestBody PersonalInfoBatchUpdateRequest request) {
        return hcPersonalInfoService.updateByIdCards(request);
    }

}
