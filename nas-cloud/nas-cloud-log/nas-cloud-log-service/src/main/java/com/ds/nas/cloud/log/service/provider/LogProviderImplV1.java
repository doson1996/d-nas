package com.ds.nas.cloud.log.service.provider;

import com.ds.nas.cloud.log.api.dubbo.LogProvider;
import com.ds.nas.cloud.log.api.io.request.LogRequest;
import com.ds.nas.cloud.log.service.LogService;
import com.ds.nas.lib.common.result.Result;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2024/4/9
 * @description
 */
@DubboService(version = "1.0")
public class LogProviderImplV1 implements LogProvider {

    @Resource
    private LogService logService;

    @Override
    public Result<String> save(LogRequest logRequest) {
        return logService.insert(logRequest);
    }

}
