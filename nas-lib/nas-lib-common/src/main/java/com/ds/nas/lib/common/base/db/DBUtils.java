package com.ds.nas.lib.common.base.db;

import com.ds.nas.lib.common.base.domain.BaseDomain;

import java.util.Date;

/**
 * @author ds
 * @date 2022/12/4
 * @description db操作工具类
 */
public class DBUtils<T extends BaseDomain> {

    private static DBUtils currentDBUtils = new DBUtils();

    public static DBUtils getCurrentDBUtils() {
        return currentDBUtils;
    }

    /**
     * 操作人 - 系统
     */
    private static final String SYSTEM_OPERATOR = "system";

    private DBUtils() {
    }

    /**
     * 新增时通用字段赋值
     *
     * @param bean
     * @return
     */
    public T onCreate(T bean) {
        return onCreate(bean, SYSTEM_OPERATOR);
    }

    /**
     * 新增时通用字段赋值
     *
     * @param bean
     * @param operator 操作人
     * @return
     */
    public T onCreate(T bean, String operator) {
        bean.setCreateTime(new Date());
        bean.setCreateBy(operator);
        bean.setUpdateTime(new Date());
        bean.setUpdateBy(operator);
        return bean;
    }

    /**
     * 更新时通用字段赋值
     *
     * @param bean
     * @return
     */
    public T onUpdate(T bean) {
       return onUpdate(bean, SYSTEM_OPERATOR);
    }

    /**
     * 更新时通用字段赋值
     *
     * @param bean
     * @param operator 操作人
     * @return
     */
    public T onUpdate(T bean, String operator) {
        bean.setUpdateTime(new Date());
        bean.setUpdateBy(operator);
        return bean;
    }

}
