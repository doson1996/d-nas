package com.ds.nas.cloud.message.sms.channel.client;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ds
 * @date 2023/4/14
 * @description
 */
@Slf4j
public abstract class AbstractSMSClient implements SMSClient {

    /**
     * 上送发送结果
     *
     * @param sendResult
     */
    @Override
    public void sendUp(Boolean sendResult) {
        // todo 上送统计
        String clientName = getClientName();
        log.info("{}发送{}...", clientName, sendResult ? "成功" : "失败");
    }

    /**
     * @return
     */
    protected String getClientName() {
        return StrUtil.lowerFirst(getClass().getSimpleName());
    }

}
