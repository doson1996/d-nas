package com.ds.nas.cloud.message.sms.channel.client;

import com.ds.nas.lib.common.util.StringUtils;

/**
 * @author ds
 * @date 2023/4/11
 * @description 客户端名称
 */
public interface ClientName {

    String TENCENT_CLIENT = getClientName(TencentSMSClient.class);

    String ALI_CLIENT = getClientName(AliSMSClient.class);

    static String getClientName(Class clazz) {
        return StringUtils.lowerFirst(clazz.getSimpleName());
    }

}
