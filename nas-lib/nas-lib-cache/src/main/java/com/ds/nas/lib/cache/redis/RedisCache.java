package com.ds.nas.lib.cache.redis;

import com.ds.nas.lib.cache.Cache;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author ds
 * @date 2023/3/22
 * @description
 */
@Component("redisCache")
public class RedisCache implements Cache {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public Boolean delete(String key) {
        redisUtil.delete(key);
        return true;
    }

    @Override
    public Integer delete(Collection<String> keys) {
        redisUtil.delete(keys);
        return keys.size();
    }

    @Override
    public Boolean set(String key, Object value) {
        if (value instanceof String) {
            redisUtil.set(key, (String) value);
            return true;
        }
        return false;
    }

    @Override
    public Object get(String key) {
        return redisUtil.get(key);
    }

}
