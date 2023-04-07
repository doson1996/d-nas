package com.ds.nas.cloud.message.strategy;

import com.ds.nas.cloud.message.client.SMSClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ds
 * @date 2023/4/7
 * @description 策略上下文，用于管理策略的注册和获取
 */
public class StrategyContext {

    /**
     * 短信客户端
     */
    protected final Map<String, SMSClient> smsClientMap = new ConcurrentHashMap<>();

    /**
     * 注册短信客户端到smsClientMap
     * @param key
     * @param client
     */
    void register(String key, SMSClient client) {
        smsClientMap.putIfAbsent(key, client);
    }

    /**
     * 注册到smsClientMap
     * @param key
     */
    SMSClient get(String key) {
        return smsClientMap.get(key);
    }

}
