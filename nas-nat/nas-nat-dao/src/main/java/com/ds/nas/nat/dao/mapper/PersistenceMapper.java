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
     * 创建表
     * @param tableName
     */
    void createTableNat(String tableName);

    /**
     * 动态建表
     * @param tableInfo
     */
    void createTable(@Param("tableInfo") TableInfo tableInfo);

}
