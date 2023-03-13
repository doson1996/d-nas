package com.ds.nas.hc.api.fegin;

import com.ds.nas.hc.api.fegin.fallback.HealthCodeClientFallback;
import com.ds.nas.hc.dao.request.PersonalInfoBatchUpdateRequest;
import com.ds.nas.hc.dao.request.PersonalInfoRegisterRequest;
import com.ds.nas.hc.dao.response.PersonalInfoBatchUpdateResponse;
import com.ds.nas.hc.dao.response.PersonalInfoRegisterResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.hc.dao.response.PersonalInfoUpdateResponse;
import com.ds.nas.hc.dao.request.PersonalInfoUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ds
 * @date 2022/12/19
 * @descriptio contextId = "hc-personal-info" 避免
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
