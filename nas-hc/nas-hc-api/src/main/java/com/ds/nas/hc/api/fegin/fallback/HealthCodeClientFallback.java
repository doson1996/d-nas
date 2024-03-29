package com.ds.nas.hc.api.fegin.fallback;

import com.ds.nas.hc.api.fegin.HealthCodeClient;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.hc.api.io.request.HealthCodeApplyRequest;
import com.ds.nas.hc.api.io.response.HealthCodeQueryResponse;
import org.springframework.stereotype.Component;

/**
 * @author ds
 * @date 2022/12/8
 * @description
 */
@Component
public class HealthCodeClientFallback implements HealthCodeClient {

    @Override
    public Result<HealthCodeQueryResponse> query(String idCard) {
        return Result.fail("查询健康码失败!");
    }

    @Override
    public Result<StringResponse> apply(HealthCodeApplyRequest request) {
        return Result.fail("申领健康码失败!");
    }

}
