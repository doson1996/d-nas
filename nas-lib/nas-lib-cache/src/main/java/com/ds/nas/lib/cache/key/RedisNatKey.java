package com.ds.nas.lib.cache.key;

/**
 * @author ds
 * @date 2023/3/28
 * @description nas-nat redis key
 */
public interface RedisNatKey extends RedisAppKey {

    /**
     * nat key
     */
    String NAT_KEY = "nat";

    /**
     * nat key 前缀
     */
    String NAT_KEY_PREFIX = APP_KEY + SEPARATOR + NAT_KEY + SEPARATOR;

    /**
     * 批量序号redis key
     */
    String BATCH_SEQUENCE_KEY = NAT_KEY_PREFIX + "batch:sequence" + SEPARATOR;

}
