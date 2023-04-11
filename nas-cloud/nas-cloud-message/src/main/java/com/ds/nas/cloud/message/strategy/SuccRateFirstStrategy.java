package com.ds.nas.cloud.message.strategy;

import com.ds.nas.cloud.message.client.ClientName;
import com.ds.nas.cloud.message.client.SMSClient;
import com.ds.nas.cloud.message.client.SmsClientContext;

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
