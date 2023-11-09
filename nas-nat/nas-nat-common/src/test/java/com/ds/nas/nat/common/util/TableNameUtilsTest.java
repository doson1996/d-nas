package com.ds.nas.nat.common.util;

import org.junit.Test;

import java.util.Set;

/**
 * @author ds
 * @date 2023/6/25
 * @description
 */
public class TableNameUtilsTest {

    @Test
    public void test_getPreDaysTableName() {
        Set<String> tableNames = TableNameUtils.getPreDaysTableName("A", 5);
        for (String tableName : tableNames) {
            System.out.println("tableName = " + tableName);
        }
    }

}
