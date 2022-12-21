package com.ds.nas.hc.client;

import com.ds.nas.hc.client.fallback.HealthCodeClientFallback;
import com.ds.nas.hc.common.result.Result;
import com.ds.nas.hc.dao.request.PersonalInfoUpdateRequest;
import com.ds.nas.hc.dao.response.PersonalInfoUpdateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ds
 * @date 2022/12/19
 * @description
 */
@FeignClient(value = "nas-hc", path = "personal-info", fallback = HealthCodeClientFallback.class)
public interface PersonalInfoClient {

    /**
     * 根据idCard更新
     * @param request
     * @return
     */
    @PostMapping("updateByIdCard")
    Result<PersonalInfoUpdateResponse> updateByIdCard(@RequestBody PersonalInfoUpdateRequest request);

}
