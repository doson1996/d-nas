package com.ds.nas.hc.app.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ds
 * @date 2023/1/22
 * @description
 */
@Slf4j
@Order(99)
@Aspect
@Component
public class LogAspect {

    @Resource
    HttpServletRequest request;

    @Pointcut("execution(* com.ds..controller.*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public void log(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        String method = request.getMethod();
        log.info("name = {}, method = {}", name, method);
    }
}
