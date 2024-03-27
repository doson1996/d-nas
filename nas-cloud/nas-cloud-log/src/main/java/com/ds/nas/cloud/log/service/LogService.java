package com.ds.nas.cloud.log.service;

import com.ds.nas.cloud.log.io.request.LogRequest;
import com.ds.nas.lib.common.result.Result;

/**
 * @author ds
 * @date 2024/3/25 23:50
 */
public interface LogService {

    /**
     * 保存日志
     * @param request
     * @return
     */
    Result<String> insert(LogRequest request);

}
