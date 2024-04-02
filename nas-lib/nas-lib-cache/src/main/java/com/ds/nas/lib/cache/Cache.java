package com.ds.nas.lib.cache;

import java.util.Collection;

/**
 * @author ds
 * @date 2023/3/22
 * @description
 */
public interface Cache {

    /**
     * 删除
     * @param key
     * @return
     */
    Boolean delete(String key);

    /**
     * 批量删除
     * @param keys
     * @return
     */
    Integer delete(Collection<String> keys);

    /**
     * 设置指定 key 的值
     * @param key
     * @param value
     */
    Boolean set(String key, Object value);

    /**
     * 获取指定 key 的值
     * @param key
     * @return
     */
    Object get(String key);


}
