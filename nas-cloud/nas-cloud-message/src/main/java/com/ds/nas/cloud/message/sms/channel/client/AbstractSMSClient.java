package com.ds.nas.cloud.message.sms.channel.client;

import cn.hutool.core.util.StrUtil;
import com.ds.nas.lib.cache.redis.RedisUtil;
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
        // todo 上送统计
        redisUtil.set("sendUp:" + clientName, String.valueOf(sendResult));
        log.info("{}发送{}...", clientName, sendResult ? "成功" : "失败");
    }

    /**
     * @return
     */
    protected String getClientName() {
        return StrUtil.lowerFirst(getClass().getSimpleName());
    }

}
