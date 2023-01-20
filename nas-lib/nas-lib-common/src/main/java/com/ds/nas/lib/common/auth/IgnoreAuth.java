package com.ds.nas.lib.common.auth;

/**
 * @author ds
 * @date 2023/1/19
 * @description 忽略认证
 */
public @interface IgnoreAuth {

    String value() default "";

    /**
     * 排除这些角色
     * @return
     */
    String[] excludeRole() default {};

    /**
     * 排除这些权限
     * @return
     */
    String[] excludePermissions() default {};

}
