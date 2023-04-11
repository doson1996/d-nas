package com.ds.nas.cloud.message.client;

import com.ds.nas.cloud.message.channel.client.SMSClient;
import com.ds.nas.cloud.message.channel.client.TencentSMSClient;

/**
 * @author ds
 * @date 2023/4/7
 * @description
 */
public class Test {
    public static void main(String[] args) throws Throwable {
        SMSClient smsClient = TencentSMSClient.getInstance();
        String send = smsClient.send("15023266863", "123136", "测试");
        System.out.println("send = " + send);
    }

}
