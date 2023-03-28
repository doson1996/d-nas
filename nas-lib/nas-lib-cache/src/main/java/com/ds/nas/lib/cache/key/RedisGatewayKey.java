package com.ds.nas.lib.cache.key;

import com.ds.nas.lib.cache.key.RedisAppKey;

/**
 * @author ds
 * @date 2023/3/28
 * @description redis gateway key
 */
public interface RedisGatewayKey extends RedisAppKey {

    /**
     * gateway key
     */
    String GATEWAY_KEY = "gateway";

    /**
     * gateway key
     */
    String GATEWAY_KEY_PREFIX = APP_KEY + SEPARATOR + GATEWAY_KEY + SEPARATOR;

    /**
     * 忽略认证请求路径key
     */
    String GATEWAY_IGNORE_PATH_SET_KEY = GATEWAY_KEY_PREFIX + "ignorePath" + SEPARATOR;

}
