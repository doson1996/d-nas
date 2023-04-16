package com.ds.nas.lib.core.proxy;

import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ds
 * @date 2023/4/14
 * @description cglib默认实现
 */
public class DefaultCglibProxy extends BaseCglibProxy {
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("CglibProxy...");
        return method.invoke(target, args);
    }
}
