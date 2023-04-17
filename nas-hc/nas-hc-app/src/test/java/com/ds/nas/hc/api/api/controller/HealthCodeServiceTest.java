package com.ds.nas.hc.api.api.controller;

import com.ds.nas.hc.app.HcApplication;
import com.ds.nas.hc.api.io.request.PersonalInfoRegisterRequest;
import com.ds.nas.hc.service.HcPersonalInfoService;
import com.ds.nas.lib.common.util.DataGenerateUtils;
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
    public void test_batch_register() {
        PersonalInfoRegisterRequest request = new PersonalInfoRegisterRequest();
        for (int i = 0; i < 100; i++) {
            request.setName(DataGenerateUtils.generateName());
            request.setIdCard(DataGenerateUtils.getIdNo());
            request.setPhone(DataGenerateUtils.getTel());
            hcPersonalInfoService.register(request);
        }
    }

}
