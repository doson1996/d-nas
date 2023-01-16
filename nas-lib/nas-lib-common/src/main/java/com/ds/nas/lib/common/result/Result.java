package com.ds.nas.lib.common.result;

import java.io.Serializable;

/**
 * 通用返回
 *
 * @author ds
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 6625888347761546266L;

    /**
     * 返回状态码
     *
     * @see ResultCode
     */
    private int code;

    /**
     * 返回消息
     *
     * @see ResultMsg
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public Result() {
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * @return 成功返回
     */
    public static <T> Result<T> ok() {
        return new Result<>(ResultCode.SUCCESS, ResultMsg.SUCCESS_MSG, null);
    }

    /**
     * @param data 数据
     * @return 带指定数据的成功返回
     */
    public static <T> Result<T> okData(T data) {
        return new Result<>(ResultCode.SUCCESS, ResultMsg.SUCCESS_MSG, data);
    }

    /**
     * @param msg 数据
     * @return 带指定消息的成功返回
     */
    public static <T> Result<T> ok(String msg) {
        return new Result<>(ResultCode.SUCCESS, msg, null);
    }

    /**
     * @param message 消息
     * @param data    数据
     * @return 带指定消息和数据的成功返回
     */
    public static <T> Result<T> ok(String message, T data) {
        return new Result<>(ResultCode.SUCCESS, message, data);
    }

    /**
     * @return 失败返回
     */
    public static <T> Result<T> fail() {
        return new Result<>(ResultCode.FAIL, ResultMsg.FAIL_MSG, null);
    }

    /**
     * @param message 数据
     * @return 带指定数据的失败返回
     */
    public static <T> Result<T> fail(String message) {
        return new Result<>(ResultCode.FAIL, message, null);
    }

    /**
     * @param message 消息
     * @param data    数据
     * @return 带指定消息和数据的失败返回
     */
    public static <T> Result<T> fail(String message, T data) {
        return new Result<>(ResultCode.FAIL, message, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static <T> ResultBuilder<T> builder() {
        return new ResultBuilder<T>();
    }

    public static final class ResultBuilder<T> {

        private int code;

        private String message;

        private T data;

        private ResultBuilder() {
        }

        public ResultBuilder<T> withCode(int code) {
            this.code = code;
            return this;
        }

        public ResultBuilder<T> withMessage(String message) {
            this.message = message;
            return this;
        }

        public ResultBuilder<T> withData(T data) {
            this.data = data;
            return this;
        }

        /**
         * Build Result.
         *
         * @see Result
         */
        public Result<T> build() {
            Result<T> result = new Result<T>();
            result.setCode(code);
            result.setMessage(message);
            result.setData(data);
            return result;
        }
    }

}
