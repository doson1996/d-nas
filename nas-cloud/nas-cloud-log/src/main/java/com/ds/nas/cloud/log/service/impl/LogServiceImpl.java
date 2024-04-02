package com.ds.nas.cloud.log.service.impl;

import com.ds.nas.cloud.log.dao.LogDao;
import com.ds.nas.cloud.log.io.request.LogRequest;
import com.ds.nas.cloud.log.service.LogService;
import com.ds.nas.cloud.log.type.LogType;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.lib.core.context.SpringContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2024/3/25 23:55
 */
@Slf4j
@Service
public class LogServiceImpl implements LogService {

    @Override
    public Result<String> insert(LogRequest request) {
        String type = LogType.getType(request.getType());
        LogDao logDao = (LogDao) SpringContext.getContext().getBean(type + "LogDao");
        String msg = logDao.insertOne(request);
        return Result.ok(msg);
    }

}
