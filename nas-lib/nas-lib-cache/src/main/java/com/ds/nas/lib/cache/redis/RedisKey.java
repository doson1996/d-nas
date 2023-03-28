package com.ds.nas.lib.cache.redis;

/**
 * @author ds
 * @date 2023/3/28
 * @description redis key整理
 */
public interface RedisKey {

    String SEPARATOR = ":";

    String APP_KEY = "d_nas";

    /**------------------------------ GATEWAY ------------------------------**/
    String GATEWAY_KEY = "gateway";

    String GATEWAY_KEY_PREFIX = APP_KEY + SEPARATOR + GATEWAY_KEY + SEPARATOR;

    /**
     *
     */
    String GATEWAY_IGNORE_PATH_SET_KEY = GATEWAY_KEY_PREFIX + "ignorePath";

}
