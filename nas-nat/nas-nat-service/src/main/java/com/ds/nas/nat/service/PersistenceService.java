package com.ds.nas.nat.service;

import com.ds.nas.nat.common.result.Result;
import com.ds.nas.nat.dao.domain.TableInfo;

import java.util.Map;

/**
 * @author ds
 * @date 2022/12/9
 * @description
 */
public interface PersistenceService {

    /**
     * 创建表
     * @param tableName
     * @return
     */
    Result<String> createTableNat(String tableName);

    /**
     * 创建表
     * @param tableInfo
     * @return
     */
    Result<String> createTable(TableInfo tableInfo);
}
