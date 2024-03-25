package com.ds.nas.cloud.log.service.impl;

import com.ds.nas.cloud.log.dao.LogMongoDao;
import com.ds.nas.cloud.log.io.request.LogRequest;
import com.ds.nas.cloud.log.service.LogService;
import com.ds.nas.lib.common.result.Result;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2024/3/25 23:55
 */
public class LogServiceImpl implements LogService {

    @Resource
    private LogMongoDao mongoDao;

    @Override
    public Result<String> save(LogRequest request) {
        return null;
    }

}
