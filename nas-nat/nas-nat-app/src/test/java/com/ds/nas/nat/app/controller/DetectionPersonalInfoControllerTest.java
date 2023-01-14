package com.ds.nas.nat.app.controller;

import cn.hutool.http.HttpUtil;
import org.junit.Test;

/**
 * @author ds
 * @date 2022/12/12
 * @description
 */
public class DetectionPersonalInfoControllerTest {

    @Test
    public void test_batch_detection() {
        String param = "{\n" +
                "  \"batchNo\": \"cd202212110000003\",\n" +
                "  \"idCard\": \"630104200610289484\"\n" +
                "}";
        HttpUtil.post("http://localhost:8091/detection-personal/detection", param);
    }

}
