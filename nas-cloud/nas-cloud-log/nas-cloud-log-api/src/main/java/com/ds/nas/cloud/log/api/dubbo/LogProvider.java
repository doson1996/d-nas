package com.ds.nas.cloud.log.api.dubbo;

import com.ds.nas.cloud.log.api.io.request.LogRequest;
import com.ds.nas.lib.common.result.Result;

/**
 * @author ds
 * @date 2024/4/9
 * @description
 */
public interface LogProvider {

    /**
     * 存储一条日志
     * @param logRequest
     * @return
     */
    Result<String> save(LogRequest logRequest);

}
