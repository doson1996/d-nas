package com.ds.nas.cloud.message.sms.channel.client;

import com.ds.nas.cloud.message.sms.channel.client.proxy.SMSClientProxyFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ds
 * @date 2023/4/7
 * @description 短信客户端上下文，用于短信客户端的注册和获取
 */
public class SmsClientContext {

    /**
     * 是否需要代理
     * todo 可配置
     */
    private static final Boolean REQUIRE_PROXY = Boolean.TRUE;

    /**
     * 短信客户端容器
     */
    private static final Map<String, SMSClient> clientMap = new ConcurrentHashMap<>(16);

    /**
     * 注册短信客户端到容器
     *
     * @param clientName
     * @param client
     */
    public static void register(String clientName, SMSClient client) {
        // 如果需要代理
        if (REQUIRE_PROXY) {
            clientMap.putIfAbsent(clientName, getClientProxy(client));
        } else {
            clientMap.putIfAbsent(clientName, client);
        }
    }

    /**
     * 获取短信客户端
     *
     * @param clientName
     * @return
     */
    public static SMSClient get(String clientName) {
        return clientMap.get(clientName);
    }

    /**
     * 获取所有短信客户端
     *
     * @param
     * @return
     */
    public static Map<String, SMSClient> getAll() {
        return clientMap;
    }

    /**
     * 获取代理对象
     * @param client
     * @return
     */
    private static SMSClient getClientProxy(SMSClient client) {
        // 对客户端进行代理，方便扩展
        SMSClientProxyFactory proxyFactory = new SMSClientProxyFactory();
        // 返回代理对象
        return (SMSClient) proxyFactory.create(client);
    }

}
