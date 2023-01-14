package com.ds.nas.hc.api.fegin.client.fallback;

import com.ds.nas.hc.api.fegin.client.HealthCodeClient;
import com.ds.nas.hc.common.result.Result;
import com.ds.nas.hc.dao.request.HealthCodeApplyRequest;
import com.ds.nas.hc.dao.response.HealthCodeQueryResponse;
import org.springframework.stereotype.Service;

/**
 * @author ds
 * @date 2022/12/8
 * @description
 */
@Service
public class HealthCodeClientFallback implements HealthCodeClient {

    @Override
    public Result<HealthCodeQueryResponse> query(String idCard) {
        return Result.fail("查询健康码失败!");
    }

    @Override
    public Result<String> apply(HealthCodeApplyRequest request) {
        return Result.fail("申领健康码失败!");
    }

}
