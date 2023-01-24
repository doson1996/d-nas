package com.ds.nas.hc.service.provider;

import com.ds.nas.hc.api.dubbo.PersonalInfoProvider;
import com.ds.nas.hc.dao.request.PersonalInfoBatchUpdateRequest;
import com.ds.nas.hc.dao.response.PersonalInfoBatchUpdateResponse;
import com.ds.nas.hc.service.HcPersonalInfoService;
import com.ds.nas.lib.common.result.Result;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2023/1/24
 * @description
 */
@DubboService(version = "1.0")
public class PersonalInfoProviderImplV1 implements PersonalInfoProvider {

    @Resource
    private HcPersonalInfoService hcPersonalInfoService;

    @Override
    public Result<PersonalInfoBatchUpdateResponse> updateByIdCards(PersonalInfoBatchUpdateRequest request) {
        return hcPersonalInfoService.updateByIdCards(request);
    }
    
}
