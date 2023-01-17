package com.ds.nas.hc.api.dubbo;

import com.ds.nas.hc.common.result.Result;
import com.ds.nas.hc.dao.response.HealthCodeQueryResponse;
/**
 * @author ds
 * @date 2023/1/17
 * @description
 */
public interface HealthCodeProvider {

    Result<HealthCodeQueryResponse> query(String idCard);

}
