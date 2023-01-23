package com.ds.nas.nat.service.impl;

import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.nat.common.util.TableNameUtils;
import com.ds.nas.nat.dao.domain.TableInfo;
import com.ds.nas.nat.dao.mapper.PersistenceMapper;
import com.ds.nas.nat.service.PersistenceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2022/12/9
 * @description
 */
@Service
public class PersistenceServiceImpl implements PersistenceService {

    @Value("${table-name.dpi}")
    private String dpiTableName;

    @Resource
    private PersistenceMapper persistenceMapper;

    @Override
    public Result<StringResponse> createTableDpi() {
        String tableName = TableNameUtils.generateTodayTableName(dpiTableName);
        int res = persistenceMapper.createTableDpi(tableName);
        if (res == 1) {
            Result.ok("创建表[" + tableName + "]成功!");
        }
        return Result.fail("创建表[" + tableName + "]失败!");
    }

    @Override
    public Result<StringResponse> createTable(TableInfo tableInfo) {
        int res = persistenceMapper.createTable(tableInfo);
        if (res == 1) {
            Result.ok("创建表[" + tableInfo.getName() + "]成功!");
        }
        return Result.fail("创建表[" + tableInfo.getName() + "]失败!");
    }

}
