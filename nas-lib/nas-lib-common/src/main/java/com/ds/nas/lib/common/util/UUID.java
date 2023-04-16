package com.ds.nas.lib.common.util;

/**
 * @author ds
 * @date 2023/4/15
 * @description
 */
public final class UUID extends cn.hutool.core.lang.UUID {

    /**
     * 使用指定的数据构造新的 UUID。
     *
     * @param mostSigBits  用于 {@code UUID} 的最高有效 64 位
     * @param leastSigBits 用于 {@code UUID} 的最低有效 64 位
     */
    public UUID(long mostSigBits, long leastSigBits) {
        super(mostSigBits, leastSigBits);
    }

}
