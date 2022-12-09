package com.ds.nas.nat.dao.domain;

import lombok.Data;

/**
 * @author ds
 * @date 2022/12/9
 * @description 字段信息
 */
@Data
public class FieldInfo {

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段长度
     */
    private Integer length;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 是否可以为空
     */
    private boolean canNull;

    /**
     * 索引类型
     */
    private String indexType;

    /**
     * 是否自增
     */
    private Integer autoIncrement;

    /**
     * 注释
     */
    private String comment;

}
