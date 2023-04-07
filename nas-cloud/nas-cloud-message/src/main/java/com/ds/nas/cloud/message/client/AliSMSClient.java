package com.ds.nas.cloud.message.client;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ds
 * @date 2023/4/7
 * @description
 */
@Slf4j
public class AliSMSClient implements SMSClient {
    @Override
    public String send(String phone, String... params) {
        log.info("AliSMSClient send...");
        return "";
    }
}
