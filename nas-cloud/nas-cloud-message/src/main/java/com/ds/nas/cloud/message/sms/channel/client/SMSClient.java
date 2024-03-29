package com.ds.nas.cloud.message.sms.channel.client;

import java.util.List;

/**
 * @author ds
 * @date 2023/4/6
 * @description 短信客户端
 */
public interface SMSClient {

    /**
     * 发送短信
     *
     * @param phone  手机号
     * @param params 短信参数
     * @return
     */
    boolean send(String phone, List<String> params);

    /**
     * 上送发送结果
     * @param sendResult
     */
    void sendUp(Boolean sendResult);

}
