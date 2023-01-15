package com.ds.nas.hc.app.aspect;

import com.ds.nas.hc.common.exception.AuthException;
import com.ds.nas.hc.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ds
 * @date 2023/1/14
 * @description
 */
@Slf4j
@Aspect
@Component
public class AuthAspect {

    @Resource
    HttpServletRequest request;

    @Pointcut("@annotation(com.ds.nas.hc.common.annotation.AuthCheck)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void authCheck(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String token = request.getHeader("token");
        log.info("method = {}, token = {}", method, token);
        if (StringUtils.isBlank(token)) {
            throw new AuthException("认证失败!");
        }

        if (!StringUtils.equals("hc:123456", token)) {
            throw new AuthException("认证失败!");
        }
    }

}
