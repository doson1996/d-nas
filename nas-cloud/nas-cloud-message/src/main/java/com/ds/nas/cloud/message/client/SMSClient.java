package com.ds.nas.cloud.message.client;

/**
 * @author ds
 * @date 2023/4/6
 * @description 短信客户端
 */
public interface SMSClient extends ClientName {

    /**
     * 发送短信
     *
     * @param phone     手机号
     * @param params    短信参数
     * @return
     */
    String send(String phone, String... params);

}
