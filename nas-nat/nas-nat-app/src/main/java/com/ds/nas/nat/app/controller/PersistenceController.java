package com.ds.nas.nat.app.controller;

import com.ds.nas.nat.common.result.Result;
import com.ds.nas.nat.dao.domain.TableInfo;
import com.ds.nas.nat.service.PersistenceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2022/12/9
 * @description
 */
@RestController
@RequestMapping("persistence")
public class PersistenceController {

    @Resource
    private PersistenceService persistenceService;

    @PostMapping("create-table")
    public Result<String> createTable(@RequestBody TableInfo tableInfo) {
        return persistenceService.createTable(tableInfo);
    }

    @PostMapping("create-table-dpi")
    public Result<String> createTableDpi() {
        return persistenceService.createTableDpi();
    }


}
