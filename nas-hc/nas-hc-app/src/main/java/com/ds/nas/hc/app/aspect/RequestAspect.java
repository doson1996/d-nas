package com.ds.nas.hc.app.aspect;

import com.ds.nas.lib.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ds
 * @date 2023/1/26
 * @description 请求校验，只支持POST请求
 */
@Slf4j
@Order(10)
@Aspect
@Component
public class RequestAspect {

    @Resource
    HttpServletRequest request;

    @Pointcut("execution(* com.ds.nas.hc.app.controller.*Controller.*(..))")
    public void pointcut() {
    }

    /**
     * 只支持post请求
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object checkMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = request.getMethod();
        if (!StringUtils.equals(RequestMethod.POST.name(), method)) {
            throw new HttpRequestMethodNotSupportedException("非法请求!");
        }

        return joinPoint.proceed();
    }

}
