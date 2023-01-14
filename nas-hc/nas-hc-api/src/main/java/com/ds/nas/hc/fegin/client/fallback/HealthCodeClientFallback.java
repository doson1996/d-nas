package com.ds.nas.hc.fegin.client.fallback;

import com.ds.nas.hc.fegin.client.HealthCodeClient;
import com.ds.nas.hc.common.result.Result;
import com.ds.nas.hc.dao.request.HealthCodeApplyRequest;
import org.springframework.stereotype.Service;

/**
 * @author ds
 * @date 2022/12/8
 * @description
 */
@Service
public class HealthCodeClientFallback implements HealthCodeClient {

    @Override
    public Result<String> apply(HealthCodeApplyRequest request) {
        return Result.fail("申领健康码失败!");
    }

}
