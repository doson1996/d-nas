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

}
