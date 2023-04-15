package com.ds.nas.cloud.message.sms.channel.client.proxy;

/**
 * @author ds
 * @date 2023/4/14
 * @description
 */
public interface BaseProxy {

    /**
     * 创建代理对象
     * @param target
     * @return
     */
    Object create(Object target);

}
