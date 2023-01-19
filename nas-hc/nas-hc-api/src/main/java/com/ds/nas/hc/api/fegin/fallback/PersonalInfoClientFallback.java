package com.ds.nas.hc.api.fegin.fallback;

import com.ds.nas.hc.api.fegin.PersonalInfoClient;
import com.ds.nas.hc.common.result.Result;
import com.ds.nas.hc.dao.request.PersonalInfoUpdateRequest;
import com.ds.nas.hc.dao.response.PersonalInfoUpdateResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author ds
 * @date 2022/12/19
 * @description
 */
@Component
public class PersonalInfoClientFallback implements PersonalInfoClient {

    @Override
    public Result<PersonalInfoUpdateResponse> updateByIdCard(PersonalInfoUpdateRequest request) {
        return Result.fail("更新健康码失败!");
    }

}