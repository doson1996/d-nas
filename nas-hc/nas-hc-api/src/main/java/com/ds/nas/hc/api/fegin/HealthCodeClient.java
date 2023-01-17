package com.ds.nas.hc.api.fegin;

import com.ds.nas.hc.api.fegin.fallback.HealthCodeClientFallback;
import com.ds.nas.hc.common.result.Result;
import com.ds.nas.hc.dao.request.HealthCodeApplyRequest;
import com.ds.nas.hc.dao.response.HealthCodeQueryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ds
 * @date 2022/12/8
 * @description
 */
@FeignClient(value = "nas-hc", path = "health-code", fallback = HealthCodeClientFallback.class)
public interface HealthCodeClient {

    /**
     * 查询健康码
     *
     * @param idCard 身份证号
     * @return 出参
     */
    @GetMapping("query/{idCard}")
    Result<HealthCodeQueryResponse> query(@PathVariable String idCard);

    /**
     * 申领健康码
     *
     * @param request 入参
     * @return 出参
     */
    @PostMapping("apply")
    Result<String> apply(@RequestBody HealthCodeApplyRequest request);

}
