package com.ds.nas.hc.app.aspect;

import com.ds.nas.lib.common.base.annotation.CheckParam;
import com.ds.nas.lib.common.base.request.BaseRequest;
import com.ds.nas.lib.common.base.request.RequestCheck;
import com.ds.nas.lib.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author ds
 * @date 2023/1/26
 * @description service层请求参数校验切面
 */
@Slf4j
//@Order(10)
//@Aspect
//@Component
public class RequestCheckAspect {

    @Resource
    HttpServletRequest request;

    @Pointcut("execution(* com.ds.nas.hc.service.impl.*ServiceImpl.*(..))")
    public void pointcut() {
    }

    /**
     * 参数校验
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object checkParameter(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            Method method = ((MethodSignature) signature).getMethod();
        }

        return joinPoint.proceed();
    }

}
