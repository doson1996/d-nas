package com.ds.nas.cloud.log.service.impl;


import com.ds.nas.cloud.log.api.io.request.LogRequest;
import com.ds.nas.cloud.log.common.type.LogType;
import com.ds.nas.cloud.log.dao.entity.LogInfo;
import com.ds.nas.cloud.log.dao.repo.LogDao;
import com.ds.nas.cloud.log.service.LogService;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.lib.common.util.StringUtils;
import com.ds.nas.lib.core.context.SpringContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        // 默认存储在mongo
        if (StringUtils.isBlank(type))
            type = LogType.MONGO.getType();
        LogDao logDao = (LogDao) SpringContext.getContext().getBean(type + "LogDao");

        LogInfo logInfo = new LogInfo();
        logInfo.setLogJson(request.getLogJson());
        logInfo.setApp(request.getRequestPrivate().getRequestApp());
        boolean result = logDao.insertOne(logInfo);
        if (result) {
            return Result.ok();
        }

        return Result.fail();
    }

}
