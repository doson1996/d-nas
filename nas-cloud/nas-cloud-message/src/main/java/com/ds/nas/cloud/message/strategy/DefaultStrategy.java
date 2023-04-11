package com.ds.nas.cloud.message.strategy;

import com.ds.nas.cloud.message.client.ClientName;
import com.ds.nas.cloud.message.client.SMSClient;
import com.ds.nas.cloud.message.client.SmsClientContext;

/**
 * @author ds
 * @date 2023/4/7
 * @description 默认策略
 */
public class DefaultStrategy extends AbstractSendStrategy {

    @Override
    public SMSClient getClient() {
        return SmsClientContext.get(ClientName.ALI_CLIENT);
    }

}
