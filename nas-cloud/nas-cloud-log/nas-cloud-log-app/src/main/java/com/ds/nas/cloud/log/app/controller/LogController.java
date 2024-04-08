package com.ds.nas.cloud.log.app.controller;

import com.ds.nas.cloud.log.api.io.request.LogRequest;
import com.ds.nas.cloud.log.dao.entity.LogInfo;
import com.ds.nas.cloud.log.service.LogService;
import com.ds.nas.lib.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ds
 * @date 2024/3/25 23:49
 */
@Slf4j
@RestController
@RequestMapping("log")
public class LogController {

    @Resource
    private LogService logService;

    @PostMapping("insert")
    public Result<String> insert(@RequestBody LogRequest request) {
        return logService.insert(request);
    }

    @GetMapping("find-all")
    public Result<List<LogInfo>> findAll(String app) {
        return logService.findAll(app);
    }

}
