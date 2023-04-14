package com.ds.nas.cloud.message.service.impl;

import com.ds.nas.cloud.message.channel.sms.strategy.SendStrategy;
import com.ds.nas.cloud.message.channel.sms.strategy.StrategyContext;
import com.ds.nas.cloud.message.service.SmsService;
import com.ds.nas.lib.cache.key.RedisMessageKey;
import com.ds.nas.lib.cache.redis.RedisUtil;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ds
 * @date 2023/4/6
 * @description
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Resource
    RedisUtil redisUtil;

    @Override
    public Result<StringResponse> send(Map<String, Object> params) {
        SendStrategy sendStrategy = StrategyContext.getStrategy(getCurrentStrategy());
        boolean sendResult = sendStrategy.getClient()
                .send((String) params.get("phone"), (List) params.get("param"));
        return Result.ok("发送成功");
    }

    @Override
    public Result<StringResponse> setStrategy(String strategy) {
        redisUtil.set(RedisMessageKey.MESSAGE_STRATEGY_KEY, strategy);
        String msg = "设置短信发送策略成功[" + strategy + "]";
        return Result.ok(msg, StringResponse.builder().withData(msg).build());
    }

    @Override
    public Result<StringResponse> currentStrategy() {
        String currentStrategy = getCurrentStrategy();
        String msg = "当前短信发送策略[" + currentStrategy + "]";
        return Result.ok(msg, StringResponse.builder().withData(currentStrategy).build());
    }

    @Override
    public Result<Set<String>> allStrategy() {
        Map<String, SendStrategy> strategyMap = StrategyContext.allStrategy();
        return Result.ok("获取全部策略成功", strategyMap.keySet());
    }

    /**
     * 获取当前执行策略
     *
     * @return
     */
    private String getCurrentStrategy() {
        String strategy;
        // todo 不止从redis获取
        strategy = redisUtil.get(RedisMessageKey.MESSAGE_STRATEGY_KEY);
        return strategy != null ? strategy : "";
    }

}
