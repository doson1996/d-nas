package com.ds.nas.cloud.message.strategy;

import com.ds.nas.cloud.message.client.SMSClient;

/**
 * @author ds
 * @date 2023/4/7
 * @description 发送策略
 */
public interface SendStrategy {

    /**
     * 获取客户端
     * @return
     */
    SMSClient getClient();

}
