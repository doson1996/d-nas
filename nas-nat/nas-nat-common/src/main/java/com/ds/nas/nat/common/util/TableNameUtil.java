package com.ds.nas.nat.common.util;


import cn.hutool.core.date.DateUtil;

/**
 * @author ds
 * @date 2022/12/11
 * @description 表名生成工具
 */
public class TableNameUtil {

    private TableNameUtil() {
        throw new AssertionError("No com.ds.nas.nat.common.util.TableNameUtil instances for you!");
    }

    /**
     * 生成带今天日期的表名
     *
     * @param tableName 表名
     * @return 返回当前日期表名 tableName_yyyyMMdd
     */
    public static String generateTodayTableName(String tableName) {
        return tableName + "_" + DateUtil.today().replaceAll("-", "");
    }

    /**
     * 生成带明天日期的表名
     *
     * @param tableName 表名
     * @return 返回明天日期表名 tableName_yyyyMMdd
     */
    public static String generateTomorrowTableName(String tableName) {
        return tableName + "_" + DateUtil.tomorrow().toDateStr().replaceAll("-", "");
    }

}
