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
        // 如果没有实现接口或者不是接口，则使用cglib
        if (target.getClass().getInterfaces().length == 0
                && !target.getClass().isInterface()) {
            return cglibProxy.create(target);
        }
        return jdkProxy.create(target);
    }
}
