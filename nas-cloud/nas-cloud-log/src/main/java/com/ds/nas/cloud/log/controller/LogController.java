package com.ds.nas.cloud.log.controller;

import com.ds.nas.cloud.log.io.request.LogRequest;
import com.ds.nas.cloud.log.service.LogService;
import com.ds.nas.lib.common.result.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2024/3/25 23:49
 */
@RestController
@RequestMapping("log")
public class LogController {

    @Resource
    private LogService logService;

    public Result<String> save(@RequestBody LogRequest request) {
        return logService.save(request);
    }

}
