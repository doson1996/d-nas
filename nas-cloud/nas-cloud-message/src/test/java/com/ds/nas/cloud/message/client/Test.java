package com.ds.nas.cloud.message.client;

/**
 * @author ds
 * @date 2023/4/7
 * @description
 */
public class Test {
    public static void main(String[] args) {
        SMSClient smsClient = TencentSMSClient.getInstance();
        String send = smsClient.send("1", "1");
    }
}
