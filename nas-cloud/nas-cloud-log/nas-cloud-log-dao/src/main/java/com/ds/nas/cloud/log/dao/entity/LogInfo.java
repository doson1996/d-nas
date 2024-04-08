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
     * 日志json
     */
    private String logJson;

    /**
     * 应用
     */
    private String app;

}
