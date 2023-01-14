package com.ds.nas.hc.api.fegin.client;

import com.ds.nas.hc.common.result.Result;
import com.ds.nas.hc.dao.request.HealthCodeApplyRequest;
import com.ds.nas.hc.api.fegin.client.fallback.HealthCodeClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ds
 * @date 2022/12/8
 * @description
 */
@FeignClient(value = "nas-hc", path = "health-code", fallback = HealthCodeClientFallback.class)
public interface HealthCodeClient {

    Result<String> apply(@RequestBody HealthCodeApplyRequest request);

}
