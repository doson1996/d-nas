package com.ds.nas.cloud.message.service.impl;

import com.ds.nas.cloud.message.service.SmsService;
import com.ds.nas.cloud.message.strategy.SendStrategy;
import com.ds.nas.cloud.message.strategy.StrategyContext;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @author ds
 * @date 2023/4/6
 * @description
 */
@Service
public class SmsServiceImpl implements SmsService {
    @Override
    public Result<StringResponse> send(Map<String, Objects> params) {
        SendStrategy sendStrategy = StrategyContext.getStrategy("PriceFirstStrategy");
        String sendResult = sendStrategy.getClient().send("1234");
        return Result.ok(sendResult);
    }
}
