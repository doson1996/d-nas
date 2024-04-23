package com.ds.nas.cloud.log.common.type;

import lombok.Getter;

/**
 * @author ds
 * @date 2024/4/2
 * @description
 */
@Getter
public enum LogType {

    MONGO(0, "mongo"),

    MYSQL(1, "mysql"),

    REDIS(2, "redis"),

    ES(3, "es");

    private final Integer code;

    private final String type;

    LogType(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public static String getType(Integer code) {
        for (LogType value : LogType.values()) {
            if (value.code.equals(code)) {
                return value.type;
            }
        }

        return null;
    }

}
