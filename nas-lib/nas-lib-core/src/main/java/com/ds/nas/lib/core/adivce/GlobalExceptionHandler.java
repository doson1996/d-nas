package com.ds.nas.lib.core.adivce;

import com.ds.nas.lib.common.exception.AuthException;
import com.ds.nas.lib.common.exception.BusinessException;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.lib.common.result.ResultEnum;
import com.ds.nas.lib.common.result.ResultMsg;
import com.ds.nas.lib.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 全局异常处理
 *
 * @author ds
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 系统异常捕获
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result<Object> handlerException(Exception e) {
        log.error("系统异常-->>" + e);

        return Result.builder()
                .withCode(ResultEnum.INTERNAL_SERVER_ERROR.getCode())
                .withMessage(ResultEnum.INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }

    /**
     * 业务异常捕获
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handlerBusinessException(BusinessException e) {
        String msg = ResultMsg.BUSINESS_EXCEPTION_MSG;
        if (StringUtils.isNotBlank(e.getMessage())) {
            msg = e.getMessage();
        }

        return Result.fail(msg);
    }

    /**
     * 认证异常捕获
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthException.class)
    public Result<Object> handlerAuthException(AuthException e) {
        String msg = ResultEnum.UNAUTHORIZED.getMessage();
        if (StringUtils.isNotBlank(e.getMessage())) {
            msg = e.getMessage();
        }

        return Result.builder()
                .withCode(ResultEnum.UNAUTHORIZED.getCode())
                .withMessage(msg)
                .build();
    }

    /**
     * 参数异常捕获
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<Void> handlerIllegalArgumentException(IllegalArgumentException e) {
        String msg = ResultMsg.PARAMETER_ERROR_MSG;
        if (StringUtils.isNotBlank(e.getMessage())) {
            msg = e.getMessage();
        }

        return Result.fail(msg);
    }

    /**
     * 文件上传异常捕获
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result<Void> handlerMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        String msg = "文件大小超出最大上传限制!";
        return Result.fail(msg);
    }

    /**
     * 请求方式异常，只支持POST请求
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<Object> handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return Result.builder()
                .withCode(ResultEnum.UNLAWFUL_REQUEST.getCode())
                .withMessage(ResultEnum.UNLAWFUL_REQUEST.getMessage())
                .build();
    }

}
