package com.ds.nas.lib.common.exception;

/**
 * 自定义业务异常
 * @author ds
 */
public class AuthException extends RuntimeException {

    public Integer code;

    public AuthException() {
    }

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
