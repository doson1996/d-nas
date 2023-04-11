package com.ds.nas.cloud.message.channel.strategy;

import com.ds.nas.cloud.message.channel.client.ClientName;
import com.ds.nas.cloud.message.channel.client.SMSClient;
import com.ds.nas.cloud.message.channel.client.SmsClientContext;

/**
 * @author ds
 * @date 2023/4/11
 * @description 成功率优先策略
 */
public class SuccRateFirstStrategy extends AbstractSendStrategy {

    @Override
    public SMSClient getClient() {
        return SmsClientContext.get(ClientName.TENCENT_CLIENT);
    }

}
