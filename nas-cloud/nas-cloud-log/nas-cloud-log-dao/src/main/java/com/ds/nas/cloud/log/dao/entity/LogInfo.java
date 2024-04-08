package com.ds.nas.cloud.log.dao.entity;

import lombok.Data;

import java.util.Map;

/**
 * @author ds
 * @date 2024/4/7
 * @description
 */
@Data
public class LogInfo {

    /**
     * 存储类型 0.mongo  1.mysql  2.redis
     */
    private  Integer type;

    /**
     * 日志json
     */
    private String logJson;

    /**
     * 日志
     */
    private Map<String, Object> log;

    /**
     * 应用
     */
    private String app;

}
