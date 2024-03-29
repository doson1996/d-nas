package com.ds.nas.nat.app.controller;

import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.nat.dao.domain.TableInfo;
import com.ds.nas.nat.service.PersistenceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2022/12/9
 * @description todo 拆分，与业务隔离,（职责、db nat_dml）
 */
@RestController
@RequestMapping("persistence")
public class PersistenceController {

    @Resource
    private PersistenceService persistenceService;

    @PostMapping("create-table")
    public Result<StringResponse> createTable(@RequestBody TableInfo tableInfo) {
        return persistenceService.createTable(tableInfo);
    }

    @PostMapping("create-table-dpi")
    public Result<StringResponse> createTableDpi() {
        return persistenceService.createTableDpi();
    }

    @PostMapping("batch-create-table-dpi")
    public Result<StringResponse> batchCreateTableDpi(int days) {
        return persistenceService.batchCreateTableDpi(days);
    }

}
