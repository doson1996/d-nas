package com.ds.nas.lib.common.util;

import org.junit.Test;

import java.util.Set;

public class DateUtilsTest {

    @Test
    public void test_getAfterDays() {
        Set<String> afterDays = DateUtils.getAfterDays(5);
        System.out.println("afterDays = " + afterDays);
    }

}
