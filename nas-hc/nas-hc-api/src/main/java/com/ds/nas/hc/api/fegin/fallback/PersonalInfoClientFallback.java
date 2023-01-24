package com.ds.nas.hc.api.fegin.fallback;

import com.ds.nas.hc.api.fegin.PersonalInfoClient;
import com.ds.nas.hc.dao.request.PersonalInfoBatchUpdateRequest;
import com.ds.nas.hc.dao.response.PersonalInfoBatchUpdateResponse;
import com.ds.nas.lib.common.result.Result;
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

    @Override
    public Result<PersonalInfoBatchUpdateResponse> updateByIdCards(PersonalInfoBatchUpdateRequest request) {
        return Result.fail("批量更新健康码失败!");
    }

}
