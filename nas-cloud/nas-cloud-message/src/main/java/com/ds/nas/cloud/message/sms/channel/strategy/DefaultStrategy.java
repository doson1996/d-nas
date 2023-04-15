package com.ds.nas.cloud.message.sms.channel.strategy;

import com.ds.nas.cloud.message.sms.channel.client.ClientName;
import com.ds.nas.cloud.message.sms.channel.client.SMSClient;
import com.ds.nas.cloud.message.sms.channel.client.SmsClientContext;
import com.ds.nas.lib.cache.redis.RedisUtil;
import org.springframework.stereotype.Component;

/**
 * @author ds
 * @date 2023/4/7
 * @description 默认策略
 */
@Component(StrategyName.DEFAULT_STRATEGY)
public class DefaultStrategy extends AbstractSendStrategy {

    /**
     * todo 预留动态getClient能力，也可以通过其他途径
     */
    private RedisUtil redisUtil;

    public DefaultStrategy(RedisUtil redisUtil) {
        register();
    }

    @Override
    public SMSClient getClient() {
        return SmsClientContext.get(ClientName.ALI_CLIENT);
    }

}
