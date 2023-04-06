package com.ds.nas.cloud.message.client;

/**
 * @author ds
 * @date 2023/4/6
 * @description 短信客户端
 */
public abstract class SmsClient {

    private String secretId;

    private String secretKey;

    /**
     * 发送短信
     * @param phone
     * @param params
     * @return
     */
    public abstract boolean send(String phone, String... params);

}
