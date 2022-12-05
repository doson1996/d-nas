package com.ds.nas.hc.api.controller;

import com.ds.nas.hc.common.result.Result;
import com.ds.nas.hc.dao.request.PersonalInfoRegisterRequest;
import com.ds.nas.hc.dao.response.PersonalInfoRegisterResponse;
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

    @PostMapping("register")
    public Result<PersonalInfoRegisterResponse> register(@RequestBody PersonalInfoRegisterRequest request) {
        return hcPersonalInfoService.register(request);
    }

}
