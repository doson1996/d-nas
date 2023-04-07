package com.ds.nas.cloud.message.strategy;

import com.ds.nas.cloud.message.client.SMSClient;
import com.ds.nas.lib.common.util.ClassUtils;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ds
 * @date 2023/4/7
 * @description 短信客户端上下文，用于短信客户端的注册和获取
 */
public class SmsClientContext {

    /**
     * 短信客户端容器
     */
    private static final Map<String, SMSClient> clientMap = new ConcurrentHashMap<>();

    /**
     * 获取短信客户端
     * @param clientName
     * @return
     */
    public static SMSClient get(String clientName) {
        return clientMap.get(clientName);
    }

    /**
     * 初始化
     */
    public static void init() {
        try {
            List<Class> clients = ClassUtils.getAllInterfaceAchieveClass(SMSClient.class);
            for (Class client : clients) {
                Constructor constructor = client.getDeclaredConstructor();
                constructor.setAccessible(true);
                clientMap.putIfAbsent(client.getSimpleName(),
                        (SMSClient)constructor.newInstance());
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}
