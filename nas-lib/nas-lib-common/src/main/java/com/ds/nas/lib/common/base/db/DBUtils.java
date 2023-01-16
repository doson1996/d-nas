package com.ds.nas.lib.common.base.db;

import com.ds.nas.hc.common.base.domain.BaseDomain;

import java.util.Date;

/**
 * @author ds
 * @date 2022/12/4
 * @description
 */
public class DBUtils<T extends BaseDomain> {

    private static DBUtils currentDBUtils = new DBUtils();

    public static DBUtils getCurrentDBUtils() {
        return currentDBUtils;
    }

    private DBUtils() {

    }

    /**
     * 通用字段赋值
     *
     * @param bean
     * @return
     */
    public T commonFieldAssignments(T bean) {
        bean.setCreateTime(new Date());
        bean.setCretaeBy("sys");
        bean.setUpdateTime(new Date());
        bean.setUpdateBy("sys");
        return bean;
    }

}
