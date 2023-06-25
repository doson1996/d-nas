package com.ds.nas.nat.common.util;

import org.junit.Test;

import java.util.List;

/**
 * @author ds
 * @date 2023/6/25
 * @description
 */
public class TableNameUtilsTest {

    @Test
    public void test_getPreDays() {
        List<String> preDays = TableNameUtils.getPreDays(3);
        System.out.println("preDays = " + preDays);
    }

}
