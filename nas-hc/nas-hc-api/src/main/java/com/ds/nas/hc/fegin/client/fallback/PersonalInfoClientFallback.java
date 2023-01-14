package com.ds.nas.hc.fegin.client.fallback;

import com.ds.nas.hc.fegin.client.PersonalInfoClient;
import com.ds.nas.hc.common.result.Result;
import com.ds.nas.hc.dao.request.PersonalInfoUpdateRequest;
import com.ds.nas.hc.dao.response.PersonalInfoUpdateResponse;
import org.springframework.stereotype.Service;

/**
 * @author ds
 * @date 2022/12/19
 * @description
 */
@Service
public class PersonalInfoClientFallback implements PersonalInfoClient {

    @Override
    public Result<PersonalInfoUpdateResponse> updateByIdCard(PersonalInfoUpdateRequest request) {
        return Result.fail("更新健康码失败!");
    }

}
