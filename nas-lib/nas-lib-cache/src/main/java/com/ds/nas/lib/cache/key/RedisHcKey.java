package com.ds.nas.lib.cache.key;

/**
 * @author ds
 * @date 2023/3/28
 * @description nas-hc redis key
 */
public interface RedisHcKey extends RedisAppKey {

    /**
     * hc key
     */
    String HC_KEY = "hc";

    /**
     * hc key 前缀
     */
    String HC_KEY_PREFIX = APP_KEY + SEPARATOR + HC_KEY + SEPARATOR;

    /**
     * health-code key
     */
    String HEALTH_CODE_KEY = HC_KEY_PREFIX + "health-code" + SEPARATOR;

}
