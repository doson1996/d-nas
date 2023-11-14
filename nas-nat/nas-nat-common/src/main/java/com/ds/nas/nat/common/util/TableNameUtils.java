package com.ds.nas.nat.common.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import com.ds.nas.lib.common.util.DateUtils;

import java.util.*;

/**
 * @author ds
 * @date 2022/12/11
 * @description 表名生成工具
 */
public class TableNameUtils {

    /**
     * 日期分割符
     */
    public static final String DATE_SEPARATOR = "-";

    /**
     * 表名分割符
     */
    public static final String TABLE_SEPARATOR = "_";

    private TableNameUtils() {
        throw new AssertionError("No com.ds.nas.nat.common.util.TableNameUtil instances for you!");
    }

    /**
     * 生成带今天日期的表名
     *
     * @param tableName 表名
     * @return 返回当前日期表名 tableName_yyyyMMdd
     */
    public static String generateTodayTableName(String tableName) {
        return generateTableName(tableName, DateUtils.today());
    }

    /**
     * 生成带明天日期的表名
     *
     * @param tableName 表名
     * @return 返回明天日期表名 tableName_yyyyMMdd
     */
    public static String generateTomorrowTableName(String tableName) {
        return generateTableName(tableName, DateUtils.tomorrow().toDateStr());
    }

    /**
     * 生成带日期的表名
     *
     * @param tableName 表名
     * @param date      日期 yyyy-MM-dd
     * @return 返回当前日期表名 tableName_yyyyMMdd
     */
    public static String generateTableName(String tableName, String date) {
        return tableName + TABLE_SEPARATOR + date.replaceAll(DATE_SEPARATOR, "");
    }

    /**
     * 根据批次号生成带日期的表名
     *
     * @param tableName 表名
     * @param batchNo   批次号
     * @return tableName_yyyyMMdd
     */
    public static String getByBatchNo(String tableName, String batchNo) {
        return tableName + TABLE_SEPARATOR + batchNo.substring(2, 10);
    }

    /**
     * 获取前几天表名
     *
     * @param days 天数
     * @return
     */
    public static Set<String> getPreDaysTableName(String tableName, int days) {
        Set<String> tableNames = new HashSet<>();
        Set<String> preDays = DateUtils.getPreDays(days);
        for (String dateStr : preDays) {
            tableNames.add(generateTableName(tableName, dateStr));
        }

        return tableNames;
    }

    /**
     * 获取后几天表名
     *
     * @param days 天数
     * @return
     */
    public static Set<String> geAfterDaysTableName(String tableName, int days) {
        Set<String> tableNames = new HashSet<>();
        Set<String> preDays = DateUtils.getAfterDays(days);
        for (String dateStr : preDays) {
            tableNames.add(generateTableName(tableName, dateStr));
        }

        return tableNames;
    }

}
