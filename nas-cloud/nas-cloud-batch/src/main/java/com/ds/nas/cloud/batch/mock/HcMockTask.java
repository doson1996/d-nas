package com.ds.nas.cloud.batch.mock;

import com.ds.nas.hc.api.fegin.PersonalInfoClient;
import com.ds.nas.hc.api.io.request.PersonalInfoRegisterRequest;
import com.ds.nas.hc.api.io.response.PersonalInfoRegisterResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.lib.common.util.DataGenerateUtils;
import com.ds.nas.lib.common.util.DataGenerateUtilsV1;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2023/3/13
 * @description
 */
@Slf4j
//@Component
public class HcMockTask {

    @Resource
    private PersonalInfoClient personalInfoClient;

    @XxlJob("regMock")
    public void regMock() {
        PersonalInfoRegisterRequest request = new PersonalInfoRegisterRequest();
        for (int i = 0; i < 2000; i++) {
            request.setName(DataGenerateUtils.generateName());
            request.setAddress(DataGenerateUtilsV1.getRandomAddress());
            request.setIdCard(DataGenerateUtils.getIdNo());
            request.setPhone(DataGenerateUtilsV1.getRandomPhone());
            Result<PersonalInfoRegisterResponse> result = personalInfoClient.register(request);
            log.info("result = {}", result);
        }
    }

}
