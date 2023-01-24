package com.ds.nas.hc.api.dubbo;

import com.ds.nas.lib.common.result.Result;
import com.ds.nas.hc.dao.response.HealthCodeQueryResponse;
/**
 * @author ds
 * @date 2023/1/17
 * @description
 */
public interface HealthCodeProvider {

    /**
     * 查询健康码
     * @param idCard
     * @return
     */
    Result<HealthCodeQueryResponse> query(String idCard);

}
