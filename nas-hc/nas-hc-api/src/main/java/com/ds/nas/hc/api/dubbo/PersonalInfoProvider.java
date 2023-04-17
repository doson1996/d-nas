package com.ds.nas.hc.api.dubbo;

import com.ds.nas.hc.api.io.request.PersonalInfoBatchUpdateRequest;
import com.ds.nas.hc.api.io.response.PersonalInfoBatchUpdateResponse;
import com.ds.nas.lib.common.result.Result;

/**
 * @author ds
 * @date 2023/1/24
 * @description
 */
public interface PersonalInfoProvider {

    /**
     * 根据idCard批量更新
     * @param request
     * @return
     */
    Result<PersonalInfoBatchUpdateResponse> updateByIdCards(PersonalInfoBatchUpdateRequest request);

}
