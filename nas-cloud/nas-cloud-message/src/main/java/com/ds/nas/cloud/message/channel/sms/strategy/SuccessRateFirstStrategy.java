package com.ds.nas.cloud.message.channel.sms.strategy;

import com.ds.nas.cloud.message.channel.sms.client.ClientName;
import com.ds.nas.cloud.message.channel.sms.client.SMSClient;
import com.ds.nas.cloud.message.channel.sms.client.SmsClientContext;
import org.springframework.stereotype.Component;

/**
 * @author ds
 * @date 2023/4/11
 * @description 成功率优先策略
 */
@Component
public class SuccessRateFirstStrategy extends AbstractSendStrategy {

    public SuccessRateFirstStrategy() {
        register();
    }

    @Override
    public SMSClient getClient() {
        return SmsClientContext.get(ClientName.TENCENT_CLIENT);
    }

}
