package com.ds.nas.lib.cache.redis;

import com.ds.nas.lib.cache.Cache;

import java.util.Collection;

/**
 * @author ds
 * @date 2023/3/22
 * @description
 */
public class RedisCache implements Cache {
    @Override
    public Boolean delete(String key) {
        return null;
    }

    @Override
    public Long delete(Collection<String> key) {
        return null;
    }

    @Override
    public void set(String key, Object value) {

    }

    @Override
    public Object get(String key) {
        return null;
    }
}
