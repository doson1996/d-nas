package com.ds.nas.nat.dao.domain;

import lombok.Data;

import java.util.List;

/**
 * @author ds
 * @date 2022/12/9
 * @description 表信息
 */
@Data
public class TableInfo {

    /**
     * 表名
     */
    private String name;

    /**
     * 主键
     */
    private String pk;

    /**
     * 字符集
     */
    private String character;

    /**
     * 字段信息
     */
    List<FieldInfo> fields;

    /**
     * 注释
     */
    private String comment;

}
