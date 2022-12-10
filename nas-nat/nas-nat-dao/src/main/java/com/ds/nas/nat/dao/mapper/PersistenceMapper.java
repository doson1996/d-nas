package com.ds.nas.nat.dao.mapper;

import com.ds.nas.nat.dao.domain.TableInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @author ds
 * @date 2022/12/9
 * @description
 */
public interface PersistenceMapper {

    /**
     * 动态建表
     * @param tableInfo
     */
    int createTable(@Param("tableInfo") TableInfo tableInfo);

    /**
     * 创建固定表
     * @param tableName
     */
    int createTableDpi(String tableName);

}
