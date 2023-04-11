package com.ds.nas.cloud.message.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ds
 * @date 2023/4/7
 * @description
 */
@Slf4j
@Component(ClientName.ALI_CLIENT)
public class AliSMSClient implements SMSClient {

    private AliSMSClient() {
        SmsClientContext.register(ClientName.ALI_CLIENT, this);
    }

    @Override
    public String send(String phone, String... params) {
        log.info("AliSMSClient send...");
        return "";
    }

}
