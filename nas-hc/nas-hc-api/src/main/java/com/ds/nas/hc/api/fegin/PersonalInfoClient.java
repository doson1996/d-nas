package com.ds.nas.hc.api.fegin;

import com.ds.nas.hc.api.fegin.fallback.HealthCodeClientFallback;
import com.ds.nas.hc.api.io.request.PersonalInfoBatchUpdateRequest;
import com.ds.nas.hc.api.io.request.PersonalInfoRegisterRequest;
import com.ds.nas.hc.api.io.response.PersonalInfoBatchUpdateResponse;
import com.ds.nas.hc.api.io.response.PersonalInfoRegisterResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.hc.api.io.response.PersonalInfoUpdateResponse;
import com.ds.nas.hc.api.io.request.PersonalInfoUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ds
 * @date 2022/12/19
 * @descriptio contextId = "hc-personal-info" 避免 The bean 'nas-hc.FeignClientSpecification'
 * could not be registered. A bean with that name has already been defined and overriding is disabled.
 */
@FeignClient(value = "nas-hc", path = "personal-info", contextId = "hc-personal-info", fallback = HealthCodeClientFallback.class)
public interface PersonalInfoClient {

    /**
     * 根据idCard更新
     *
     * @param request
     * @return
     */
    @PostMapping("updateByIdCard")
    Result<PersonalInfoUpdateResponse> updateByIdCard(@RequestBody PersonalInfoUpdateRequest request);

    /**
     * 根据idCard批量更新
     *
     * @param request
     * @return
     */
    @PostMapping("updateByIdCards")
    Result<PersonalInfoBatchUpdateResponse> updateByIdCards(@RequestBody PersonalInfoBatchUpdateRequest request);

    /**
     * 注册
     *
     * @param request
     * @return
     */
    @PostMapping("register")
    Result<PersonalInfoRegisterResponse> register(@RequestBody PersonalInfoRegisterRequest request);

}
