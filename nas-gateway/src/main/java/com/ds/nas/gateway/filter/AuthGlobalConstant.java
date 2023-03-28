package com.ds.nas.gateway.filter;

/**
 * @author ds
 */
public interface AuthGlobalConstant {

    /**
     * 管理员登录路径，忽略验证token
     */
    String ADMIN_LOGIN_PATH = "/api/auth/admin/login";

    /**
     * 用户登录路径，忽略验证token
     */
    String USER_LOGIN_PATH = "/api/auth/user/login";



}
