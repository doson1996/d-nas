package com.ds.nas.cloud.message.sms.channel.strategy;

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
    private static final Map<String, SendStrategy> strategyMap = new ConcurrentHashMap<>(16);

    /**
     * 注册策略
     *
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
    public static SendStrategy getStrategy(String key) {
        SendStrategy sendStrategy = strategyMap.get(key);
        // 没有对应发送策略时返回默认策略
        return sendStrategy != null ? sendStrategy : strategyMap.get(StrategyName.DEFAULT_STRATEGY);
    }

    /**
     * 获取默认策略
     * @return
     */
    public static SendStrategy getDefaultStrategy() {
        return getStrategy(StrategyName.DEFAULT_STRATEGY);
    }

    /**
     * 获取所有策略
     */
    public static Map<String, SendStrategy> allStrategy() {
        return strategyMap;
    }

}
