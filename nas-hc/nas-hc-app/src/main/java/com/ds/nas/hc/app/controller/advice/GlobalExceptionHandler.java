package com.ds.nas.hc.app.controller.advice;

import cn.hutool.core.util.StrUtil;

import com.ds.nas.hc.common.exception.BusinessException;
import com.ds.nas.hc.common.result.Result;
import com.ds.nas.hc.common.result.ResultEnum;
import com.ds.nas.hc.common.result.ResultMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 全局异常处理
 * @author ds
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 系统异常捕获
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result<Object> handlerException(Exception e){
        log.error("系统异常-->>" + e);

        return Result.builder()
                .withCode(ResultEnum.INTERNAL_SERVER_ERROR.getCode())
                .withMessage(ResultEnum.INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }

    /**
     * 业务异常捕获
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handlerBusinessException(BusinessException e){
        String msg = ResultMsg.BUSINESS_EXCEPTION_MSG;
        if (StrUtil.isNotBlank(e.getMessage())) {
            msg = e.getMessage();
        }

        return Result.fail(msg);
    }

    /**
     * 参数异常捕获
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<Void> handlerIllegalArgumentException(IllegalArgumentException e){
        String msg = ResultMsg.PARAMETER_ERROR_MSG;
        if (StrUtil.isNotBlank(e.getMessage())) {
            msg = e.getMessage();
        }

        return Result.fail(msg);
    }

    /**
     * 文件上传异常捕获
     * @param e
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result<Void> handlerMaxUploadSizeExceededException(MaxUploadSizeExceededException e){
        String msg = "文件大小超出最大上传限制";
        return Result.fail(msg);
    }

}
