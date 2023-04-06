package com.ds.nas.cloud.message.service;

/**
 * @author ds
 * @date 2023/4/6
 * @description
 */
public interface SmsService {

    /**
     * 发送
     * @param context
     * @return
     */
    boolean send(String context);

}
