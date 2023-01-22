package com.ds.nas.hc.app.controller;

import com.ds.nas.lib.common.result.Result;
import com.ds.nas.hc.dao.request.PersonalInfoRegisterRequest;
import com.ds.nas.hc.dao.request.PersonalInfoUpdateRequest;
import com.ds.nas.hc.dao.response.PersonalInfoRegisterResponse;
import com.ds.nas.hc.dao.response.PersonalInfoUpdateResponse;
import com.ds.nas.hc.service.HcPersonalInfoService;
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
    @PostMapping("register")
    public Result<PersonalInfoRegisterResponse> register(@RequestBody PersonalInfoRegisterRequest request) {
        return hcPersonalInfoService.register(request);
    }

    /**
     * 根据主键更新
     * @param request 入参
     * @return
     */
    @PostMapping("updateByIdCard")
    public Result<PersonalInfoUpdateResponse> updateByIdCard(@RequestBody PersonalInfoUpdateRequest request) {
        return hcPersonalInfoService.updateByIdCard(request);
    }

}
