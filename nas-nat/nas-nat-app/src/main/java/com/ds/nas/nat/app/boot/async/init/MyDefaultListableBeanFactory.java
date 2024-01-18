package com.ds.nas.nat.app.boot.async.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ds
 * @date 2024/1/17
 * @description
 */
@Slf4j
public class MyDefaultListableBeanFactory extends DefaultListableBeanFactory {

    private static final ThreadPoolExecutor THREAD_POOL =
            new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));

    public MyDefaultListableBeanFactory(BeanFactory parentBeanFactory) {
        super(parentBeanFactory);
    }

    @Override
    protected void invokeInitMethods(String beanName, Object bean, RootBeanDefinition mbd) throws Throwable {
        // 异步执行
        if (bean.getClass().getAnnotation(AsyncInit.class) != null) {
            THREAD_POOL.execute(() -> {
                try {
                    super.invokeInitMethods(beanName, bean, mbd);
                } catch (Throwable e) {
                    log.error("异步执行初始化方法异常, e", e);
                }
            });
        } else {
            super.invokeInitMethods(beanName, bean, mbd);
        }
    }
}
