package com.ds.nas.lib.cache.key;

/**
 * @author ds
 * @date 2023/3/28
 * @description nas-auth redis key
 */
public interface RedisAuthKey extends RedisAppKey {

    /**
     * auth key
     */
    String AUTH_KEY = "auth";

    /**
     * auth key prefix
     */
    String AUTH_KEY_PREFIX = APP_KEY + SEPARATOR + AUTH_KEY + SEPARATOR;

    /**
     * login token key
     */
    String LOGIN_TOKEN_KEY = AUTH_KEY_PREFIX + "login-token" + SEPARATOR;

}
