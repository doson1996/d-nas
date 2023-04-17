package com.ds.nas.hc.service.provider;

import com.ds.nas.hc.api.dubbo.HealthCodeProvider;
import com.ds.nas.hc.api.io.request.HealthCodeQueryRequest;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.hc.api.io.response.HealthCodeQueryResponse;
import com.ds.nas.hc.service.HcPersonalInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2023/1/17
 * @description
 */
@DubboService(version = "1.0")
public class HealthCodeProviderImplV1 implements HealthCodeProvider {

    @Resource
    private HcPersonalInfoService personalInfoService;

    @Override
    public Result<HealthCodeQueryResponse> query(String idCard) {
        HealthCodeQueryRequest request = new HealthCodeQueryRequest();
        request.setIdCard(idCard);
        return personalInfoService.queryByIdCard(request);
    }
}
