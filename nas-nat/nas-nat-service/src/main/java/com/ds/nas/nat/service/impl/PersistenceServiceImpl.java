package com.ds.nas.nat.service.impl;

import cn.hutool.core.date.DateUtil;
import com.ds.nas.nat.common.result.Result;
import com.ds.nas.nat.dao.domain.TableInfo;
import com.ds.nas.nat.dao.mapper.PersistenceMapper;
import com.ds.nas.nat.service.PersistenceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author ds
 * @date 2022/12/9
 * @description
 */
@Service
public class PersistenceServiceImpl implements PersistenceService {

    @Value("table-name:dpi")
    private String dpiTableName;

    @Resource
    private PersistenceMapper persistenceMapper;

    @Override
    public Result<String> createTableDpi() {
        String tableName = dpiTableName + DateUtil.now();
        int res = persistenceMapper.createTableDpi(tableName);
        if (res == 1) {
            Result.ok("创建表[" + tableName + "]成功!");
        }
        return Result.ok("创建表[" + tableName + "]失败!");
    }

    @Override
    public Result<String> createTable(TableInfo tableInfo) {
        int res = persistenceMapper.createTable(tableInfo);
        if (res == 1) {
            Result.ok("创建表[" + tableInfo.getName() + "]成功!");
        }
        return Result.ok("创建表[" + tableInfo.getName() + "]失败!");
    }

}
