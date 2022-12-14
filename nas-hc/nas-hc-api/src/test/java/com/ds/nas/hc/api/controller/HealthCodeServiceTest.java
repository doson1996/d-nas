package com.ds.nas.hc.api.controller;

import com.ds.nas.hc.api.HcApplication;
import com.ds.nas.hc.common.util.DataGenerateUtil;
import com.ds.nas.hc.dao.request.HealthCodeApplyRequest;
import com.ds.nas.hc.service.HcPersonalInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2022/12/4
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HcApplication.class)
public class HealthCodeServiceTest {

    @Resource
    private HcPersonalInfoService hcPersonalInfoService;

    @Test
    public void test_batch_apply() {

        HealthCodeApplyRequest request = new HealthCodeApplyRequest();
        for (int i = 0; i < 10000; i++) {
            request.setIdCard(DataGenerateUtil.getIdNo());
            hcPersonalInfoService.apply(request);
        }

    }

}
