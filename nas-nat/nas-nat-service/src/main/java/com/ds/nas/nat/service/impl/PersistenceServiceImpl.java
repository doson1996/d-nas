package com.ds.nas.nat.service.impl;

import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.lib.common.util.StringUtils;
import com.ds.nas.nat.common.util.TableNameUtils;
import com.ds.nas.nat.dao.domain.TableInfo;
import com.ds.nas.nat.dao.mapper.PersistenceMapper;
import com.ds.nas.nat.service.PersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2022/12/9
 * @description
 */
@Slf4j
@Service
public class PersistenceServiceImpl implements PersistenceService {

    @Value("${table-name.dpi}")
    private String dpiTableName;

    @Resource
    private PersistenceMapper persistenceMapper;

    @Override
    public Result<StringResponse> createTableDpi() {
        boolean success;
        String tableName = "";
        try {
            tableName = TableNameUtils.generateTodayTableName(dpiTableName);
            if (checkTable(tableName)) {
                return Result.fail("创建表[" + tableName + "]失败, 表已存在!");
            }

            if (StringUtils.isBlank(tableName)) {
                return Result.fail("创建表[" + tableName + "]失败!");
            }
            persistenceMapper.createTableDpi(tableName);
            success = checkTable(tableName);
        } catch (Exception e) {
            log.error("PersistenceServiceImpl.createTableDpi ex:", e);
            success = false;
        }

        return success ? Result.ok("创建表[" + tableName + "]成功!") : Result.fail("创建表[" + tableName + "]失败!");
    }

    @Override
    public Result<StringResponse> createTable(TableInfo tableInfo) {
        boolean success;

        String tableName = tableInfo.getName();
        try {
            if (checkTable(tableName)) {
                return Result.fail("创建表[" + tableName + "]失败, 表已存在!");
            }

            persistenceMapper.createTable(tableInfo);
            success = checkTable(tableName);
        } catch (Exception e) {
            log.error("PersistenceServiceImpl.createTable ex:", e);
            success = false;
        }

        return success ? Result.ok("创建表[" + tableName + "]成功!") : Result.fail("创建表[" + tableName + "]失败!");
    }

    /**
     * 判断表是否存在
     *
     * @param tableName
     * @return
     */
    private boolean checkTable(String tableName) {
        return StringUtils.equals(tableName, persistenceMapper.checkTable(tableName));
    }

}
