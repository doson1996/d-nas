package com.ds.nas.cloud.message.client;

/**
 * @author ds
 * @date 2023/4/6
 * @description 腾讯短信客户端
 */
public class TencentSmsClient extends SmsClient {

    @Override
    public boolean send(String phone, String... params) {
        return false;
    }

}
