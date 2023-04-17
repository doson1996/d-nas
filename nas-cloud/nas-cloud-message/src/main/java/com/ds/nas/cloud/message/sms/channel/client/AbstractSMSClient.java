package com.ds.nas.cloud.message.sms.channel.client;

import com.ds.nas.lib.cache.key.RedisSMSKey;
import com.ds.nas.lib.cache.redis.RedisUtil;
import com.ds.nas.lib.common.util.StringUtils;
import com.ds.nas.lib.core.context.SpringContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ds
 * @date 2023/4/14
 * @description
 */
@Slf4j
public abstract class AbstractSMSClient implements SMSClient {

    private RedisUtil redisUtil;

    /**
     * 上送发送结果
     *
     * @param sendResult
     */
    @Override
    public void sendUp(Boolean sendResult) {
        if (redisUtil == null)
            redisUtil = SpringContext.getContext().getBean(RedisUtil.class);
        String clientName = getClientName();
        sendUp(clientName, sendResult);
        log.info("{}发送{}...", clientName, sendResult ? "成功" : "失败");
    }

    /**
     * 上送统计
     *
     * @param clientName
     * @param sendResult
     */
    private void sendUp(String clientName, Boolean sendResult) {
        if (sendResult) {
            redisUtil.incrBy(RedisSMSKey.SMS_CLIENT_SEND_SUCCESS_KEY + clientName, 1);
        }
        redisUtil.incrBy(RedisSMSKey.SMS_CLIENT_SEND_TOTAL_KEY + clientName, 1);
    }

    /**
     * @return
     */
    protected String getClientName() {
        return StringUtils.lowerFirst(getClass().getSimpleName());
    }

}
