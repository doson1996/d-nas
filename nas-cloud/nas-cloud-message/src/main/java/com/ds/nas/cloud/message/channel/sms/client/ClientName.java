package com.ds.nas.cloud.message.channel.sms.client;

import cn.hutool.core.util.StrUtil;

/**
 * @author ds
 * @date 2023/4/11
 * @description 客户端名称
 */
public interface ClientName {

    String TENCENT_CLIENT = getClientName(TencentSMSClient.class);

    String ALI_CLIENT = getClientName(AliSMSClient.class);

    static String getClientName(Class clazz) {
        return StrUtil.lowerFirst(clazz.getSimpleName());
    }

}
