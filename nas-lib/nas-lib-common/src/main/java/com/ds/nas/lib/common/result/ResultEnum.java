package com.ds.nas.lib.common.result;

/**
 * @author ds
 * @see org.springframework.http.HttpStatus
 */
public enum ResultEnum {

    /**
     * 业务处理成功
     */
    OK(ResultCode.SUCCESS, "ok"),

    /**
     * 业务处理失败
     */
    FAIL(ResultCode.FAIL, "fail"),

    /**
     * 服务器发生了未知错误
     */
    INTERNAL_SERVER_ERROR(500, "系统繁忙，请稍后重试"),

    /**
     * 访问未经授权
     */
    UNAUTHORIZED(401, "Unauthorized"),

    /**
     * 用户端错误 1000 - 1999
     */
    USER_CLIENT_ERROR(1000, "用户端错误"),
    USER_ACCOUNT_FORBIDDEN(1001, "账号已被禁用"),
    USER_NOT_EXIST(1002, "用户不存在"),
    USER_HAS_EXISTED(1003, "用户已存在"),
    USER_LOGIN_ERROR(1004, "账号或密码错误"),

    /**
     * 请求参数异常 2000 - 2999
     */
    PARAMETER_ERROR(2000, "请求参数异常"),
    PARAM_IS_INVALID(2001, "参数无效"),
    PARAM_IS_BLANK(2002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(2003, "参数类型错误"),
    PARAM_NOT_COMPLETE(2004, "参数缺失"),
    REQUEST_JSON_PARSING_FAILED(2004, "请求JSON解析失败"),

    /**
     * 接口调用异常 3000 - 3999
     */
    INTERFACE_CALL_ERROR(3000, "接口调用异常"),
    INTERFACE_INNER_INVOKE_ERROR(3001, "内部系统接口调用异常"),
    INTERFACE_OUTTER_INVOKE_ERROR(3002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(3003, "接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(3004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(3005, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(3006, "接口负载过高");

    /**
     * code
     */
    private final int code;

    /**
     * 消息
     */
    private final String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
