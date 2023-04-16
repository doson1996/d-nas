package com.ds.nas.lib.core.proxy;

import java.lang.reflect.InvocationHandler;

/**
 * @author ds
 * @date 2023/4/14
 * @description jdk默认实现
 */
public class DefaultJdkProxy extends BaseJdkProxy {
    @Override
    protected InvocationHandler invocationHandler() {
        return (proxy, method, args) -> {
            System.out.println("jdk proxy...");
            return method.invoke(target, args);
        };
    }
}
