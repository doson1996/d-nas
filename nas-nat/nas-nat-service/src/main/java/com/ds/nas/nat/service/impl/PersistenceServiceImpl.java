package com.ds.nas.nat.service.impl;

import com.ds.nas.nat.common.result.Result;
import com.ds.nas.nat.dao.domain.TableInfo;
import com.ds.nas.nat.dao.mapper.PersistenceMapper;
import com.ds.nas.nat.service.PersistenceService;
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

    @Resource
    private PersistenceMapper persistenceMapper;

    @Override
    public Result<String> createTableNat(String tableName) {
        persistenceMapper.createTableNat(tableName);
        return Result.ok("创建表成功!");
    }

    @Override
    public Result<String> createTable(TableInfo tableInfo) {

        persistenceMapper.createTable(tableInfo);
        return Result.ok("创建表[" + tableInfo.getName() + "]成功!");
    }

}
