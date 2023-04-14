package com.ds.nas.lib.cache.key;

/**
 * @author ds
 * @date 2023/4/12
 * @description
 */
public interface RedisMessageKey extends RedisAppKey {

    /**
     * message key
     */
    String MESSAGE_KEY = "message";

    /**
     * message key 前缀
     */
    String MESSAGE_KEY_PREFIX = APP_KEY + SEPARATOR + MESSAGE_KEY + SEPARATOR;

    /**
     * 客户端集合 key
     */
    String MESSAGE_CLIENT_KEY = MESSAGE_KEY_PREFIX + "client" + SEPARATOR;

    /**
     * 验证码集合 key
     */
    String MESSAGE_CAPTCHA_KEY = MESSAGE_KEY_PREFIX + "captcha" + SEPARATOR;

    /**
     * 发送短信当前策略
     */
    String MESSAGE_STRATEGY_KEY = MESSAGE_KEY_PREFIX + "sms-current-strategy";

}
