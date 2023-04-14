package com.ds.nas.cloud.message.channel.sms.client.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * @author ds
 * @date 2023/4/14
 * @description cglib代理
 */
public abstract class BaseCglibProxy implements MethodInterceptor, BaseProxy {

    /**
     * 被代理对象
     */
    protected Object target;

    @Override
    public Object create(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

}
