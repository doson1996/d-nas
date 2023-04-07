package com.ds.nas.cloud.message.strategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ds
 * @date 2023/4/7
 * @description 策略上下文，用于管理策略的注册和获取
 */
public class StrategyContext {

    /**
     * 发送策略容器
     */
    private static final Map<String, SendStrategy> strategyMap = new ConcurrentHashMap<>();

    /**
     * 注册策略
     * @param key
     * @param strategy
     */
    static void registerStrategy(String key, SendStrategy strategy) {
        strategyMap.putIfAbsent(key, strategy);
    }

    /**
     * 获取策略
     *
     * @param key
     */
    SendStrategy getStrategy(String key) {
        return strategyMap.get(key);
    }

}
