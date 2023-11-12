package com.ds.nas.nat.dao.mapper;

import com.ds.nas.nat.dao.domain.TableInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author ds
 * @date 2022/12/9
 * @description
 */
@Repository
public interface PersistenceMapper {

    /**
     * 动态建表
     * @param tableInfo
     * @return
     */
    int createTable(@Param("tableInfo") TableInfo tableInfo);

    /**
     * 创建固定表
     * @param tableName
     * @return
     */
    int createTableDpi(String tableName);

    /**
     * 判断表是否存在
     * @param tableName
     * @return
     */
    String checkTable(String tableName);

}
