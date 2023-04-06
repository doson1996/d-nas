package com.ds.nas.cloud.message.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ds
 * @date 2023/4/6
 * @description 短信客户端
 */
public abstract class SmsClient {

    /**
     * 短信客户端
     */
    private final Map<String, SmsClient> smsClientMap = new ConcurrentHashMap<>();

    private String secretId;

    private String secretKey;

    /**
     * 发送短信
     * @param phone
     * @param params
     * @return
     */
    public abstract boolean send(String phone, String... params);

    /**
     * 注册短信客户端到smsClientMap
     * @param key
     * @param client
     */
    void register(String key,SmsClient client) {
        smsClientMap.put(key, client);
    }

    /**
     * 注册到smsClientMap
     * @param key
     */
    SmsClient get(String key) {
        return smsClientMap.get(key);
    }

}
