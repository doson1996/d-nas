package com.ds.nas.cloud.message.strategy;

import com.ds.nas.cloud.message.client.SMSClient;

/**
 * @author ds
 * @date 2023/4/7
 * @description 价格优先策略
 */
public class PriceFirstStrategy extends AbstractSendStrategy {
    @Override
    public SMSClient getClient() {
        return null;
    }
}
