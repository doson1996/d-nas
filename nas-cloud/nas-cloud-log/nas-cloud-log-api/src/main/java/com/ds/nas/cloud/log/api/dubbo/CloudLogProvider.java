package com.ds.nas.cloud.log.api.dubbo;

import com.ds.nas.cloud.log.api.io.request.LogRequest;
import com.ds.nas.lib.common.result.Result;

import java.util.List;

/**
 * @author ds
 * @date 2024/4/9
 * @description
 */
public interface CloudLogProvider {

    /**
     * 存储一条日志
     * @param logRequest
     * @return
     */
    Result<String> save(LogRequest logRequest);

    /**
     * 存储多条日志
     * @param logRequests
     * @return
     */
    Result<String> save(List<LogRequest> logRequests);

}
