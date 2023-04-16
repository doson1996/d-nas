package com.ds.nas.lib.core.proxy;

/**
 * @author ds
 * @date 2023/4/14
 * @description 动态代理工厂
 */
public class ProxyFactory implements BaseProxy {

    private final BaseProxy jdkProxy = new DefaultJdkProxy();

    private final BaseProxy cglibProxy = new DefaultCglibProxy();

    @Override
    public Object create(Object target) {
        if (target.getClass().getInterfaces().length == 0) {
            return cglibProxy.create(target);
        }
        return jdkProxy.create(target);
    }
}
