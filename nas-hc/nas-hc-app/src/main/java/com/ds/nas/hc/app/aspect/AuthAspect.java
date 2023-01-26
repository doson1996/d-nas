package com.ds.nas.hc.app.aspect;

import com.ds.nas.lib.common.auth.AuthCheck;
import com.ds.nas.lib.common.exception.AuthException;
import com.ds.nas.lib.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ds
 * @date 2023/1/14
 * @description 权限校验
 */
@Slf4j
@Order(20)
@Aspect
@Component
public class AuthAspect {

    @Resource
    HttpServletRequest request;

    @Pointcut("@annotation(com.ds.nas.lib.common.auth.AuthCheck)")
    public void pointcut() {
    }

    @Before("@annotation(authCheck)")
    public void authCheck(JoinPoint joinPoint, AuthCheck authCheck) {
        String name = joinPoint.getSignature().getName();
        String token = request.getHeader("token");
        log.info("method-name = {}, token = {}", name, token);
        if (StringUtils.isBlank(token)) {
            throw new AuthException("认证失败!");
        }

        if (!StringUtils.equals("hc:123456", token)) {
            throw new AuthException("认证失败!");
        }
    }

}
