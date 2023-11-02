package com.ds.nas.lib.common.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ds
 * @date 2023/3/8
 * @description 参数检验注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Check {

    /**
     * 内容
     *
     * @return
     */
    String value() default "";

    /**
     * 是否必输
     *
     * @return
     */
    boolean require() default false;

    /**
     * 最大长度,大于0才校验（目前只支持字符串）
     *
     * @return
     */
    int maxLen() default 0;

    /**
     * 是否纯数字（只针对字符串）
     *
     * @return
     */
    boolean onlyNumber() default false;

    /**
     * 是否身份证号码
     *
     * @return
     */
    boolean idCard() default false;

    /**
     * 字段校验失败返回消息
     *
     * @return
     */
    String msg() default "";

}
