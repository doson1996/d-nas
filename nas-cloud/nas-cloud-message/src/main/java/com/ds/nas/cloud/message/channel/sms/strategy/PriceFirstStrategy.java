package com.ds.nas.cloud.message.channel.sms.strategy;

import com.ds.nas.cloud.message.channel.sms.client.ClientName;
import com.ds.nas.cloud.message.channel.sms.client.SMSClient;
import com.ds.nas.cloud.message.channel.sms.client.SmsClientContext;
import org.springframework.stereotype.Component;

/**
 * @author ds
 * @date 2023/4/7
 * @description 价格优先策略
 */
@Component
public class PriceFirstStrategy extends AbstractSendStrategy {

    private PriceFirstStrategy( ) {
        register();
    }

    @Override
    public SMSClient getClient() {
        return SmsClientContext.get(ClientName.ALI_CLIENT);
    }

}
