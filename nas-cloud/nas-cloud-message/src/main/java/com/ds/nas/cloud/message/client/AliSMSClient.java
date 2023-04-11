package com.ds.nas.cloud.message.client;

import com.ds.nas.cloud.message.strategy.SmsClientContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ds
 * @date 2023/4/7
 * @description
 */
@Slf4j
public class AliSMSClient implements SMSClient {

    private static final AliSMSClient INSTANCE = new AliSMSClient();

    private AliSMSClient() {
        SmsClientContext.register(ALI_CLIENT, this);
    }

    public static AliSMSClient getInstance() {
        return INSTANCE;
    }

    @Override
    public String send(String phone, String... params) {
        log.info("AliSMSClient send...");
        return "";
    }

}
