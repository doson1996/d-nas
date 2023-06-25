package com.ds.nas.nat.common.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import com.ds.nas.lib.common.util.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        return tableName + TABLE_SEPARATOR + DateUtils.today().replaceAll(DATE_SEPARATOR, "");
    }

    /**
     * 生成带明天日期的表名
     *
     * @param tableName 表名
     * @return 返回明天日期表名 tableName_yyyyMMdd
     */
    public static String generateTomorrowTableName(String tableName) {
        return tableName + TABLE_SEPARATOR + DateUtils.tomorrow().toDateStr().replaceAll(DATE_SEPARATOR, "");
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
     * 获取当前日期到前几天的日期集合
     *
     * @param days
     * @return
     */
    public static List<String> getPreDays(int days) {
        List<String> result = new ArrayList<>(days);
        result.add(DateUtils.today());
        for (int i = 1; i < days; i++) {
            DateTime offset = DateUtils.offset(new Date(), DateField.DAY_OF_YEAR, -i);
            result.add(offset.toDateStr());
        }
        return result;
    }

}
