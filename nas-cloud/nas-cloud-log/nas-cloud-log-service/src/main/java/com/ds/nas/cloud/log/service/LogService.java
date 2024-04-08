package com.ds.nas.cloud.log.service;

import com.ds.nas.cloud.log.api.io.request.LogRequest;
import com.ds.nas.cloud.log.dao.entity.LogInfo;
import com.ds.nas.lib.common.result.Result;

import java.util.List;

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

    /**
     * 查询所有日志
     * @param app
     * @return
     */
    Result<List<LogInfo>> findAll(String app);

}
