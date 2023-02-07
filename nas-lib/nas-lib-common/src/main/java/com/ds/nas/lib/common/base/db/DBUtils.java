package com.ds.nas.lib.common.base.db;

import com.ds.nas.lib.common.base.domain.BaseDomain;

import java.util.Date;

/**
 * @author ds
 * @date 2022/12/4
 * @description db操作工具类
 */
public class DBUtils {

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
    public static void onCreate(BaseDomain bean) {
        onCreate(bean, SYSTEM_OPERATOR);
    }

    /**
     * 新增时通用字段赋值
     *
     * @param bean
     * @param operator 操作人
     * @return
     */
    public static void onCreate(BaseDomain bean, String operator) {
        bean.setCreateTime(new Date());
        bean.setCreateBy(operator);
        bean.setUpdateTime(new Date());
        bean.setUpdateBy(operator);
    }

    /**
     * 更新时通用字段赋值
     *
     * @param bean
     * @return
     */
    public static void onUpdate(BaseDomain bean) {
        onUpdate(bean, SYSTEM_OPERATOR);
    }

    /**
     * 更新时通用字段赋值
     *
     * @param bean
     * @param operator 操作人
     * @return
     */
    public static void onUpdate(BaseDomain bean, String operator) {
        bean.setUpdateTime(new Date());
        bean.setUpdateBy(operator);
    }

}
