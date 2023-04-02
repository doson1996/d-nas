package com.ds.nas.lib.common.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ds
 * @date 2023/1/20
 * @description 记录日志
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Log {

    /**
     * 日志内容
     * @return
     */
    String value() default "";

    /**
     * 记录日志备注
     * @return
     */
    String remark() default "";

    /**
     * 记录日志类型
     * @return DB 数据库
     *         FILE 文件
     *         MQ 消息队列
     */
    LogType type() default LogType.FILE;

    /**
     * 指定打印的日志 logger name
     * @return
     */
    String name() default  "";

}
