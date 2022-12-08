package com.ds.nas.hc.client;

import com.ds.nas.hc.client.fallback.HealthCodeClientFallBack;
import com.ds.nas.hc.common.result.Result;
import com.ds.nas.hc.dao.request.HealthCodeApplyRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ds
 * @date 2022/12/8
 * @description
 */
@FeignClient(value = "nas-hc", path = "health-code", fallback = HealthCodeClientFallBack.class)
public interface HealthCodeClient {

    Result<String> apply(@RequestBody HealthCodeApplyRequest request);

}
