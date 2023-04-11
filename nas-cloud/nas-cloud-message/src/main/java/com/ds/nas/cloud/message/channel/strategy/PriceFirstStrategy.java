package com.ds.nas.cloud.message.channel.strategy;

import com.ds.nas.cloud.message.channel.client.ClientName;
import com.ds.nas.cloud.message.channel.client.SMSClient;
import com.ds.nas.cloud.message.channel.client.SmsClientContext;

/**
 * @author ds
 * @date 2023/4/7
 * @description 价格优先策略
 */
public class PriceFirstStrategy extends AbstractSendStrategy {
    
    @Override
    public SMSClient getClient() {
        return SmsClientContext.get(ClientName.ALI_CLIENT);
    }

}
