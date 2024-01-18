package com.ds.nas.nat.app.boot.async.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.lang.reflect.Field;

/**
 * @author ds
 * @date 2024/1/17
 * @description
 */
@Slf4j
public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.info("MyApplicationContextInitializer initialize...");
        try {
            MyDefaultListableBeanFactory myDefaultListableBeanFactory = new MyDefaultListableBeanFactory(applicationContext.getBeanFactory());

            Field beanFactoryField = GenericApplicationContext.class.getDeclaredField("beanFactory");
            beanFactoryField.setAccessible(true);
            beanFactoryField.set(applicationContext, myDefaultListableBeanFactory);
            log.info("替换beanFactory成功...");
        } catch (Exception e) {
            log.error("替换beanFactory失败...");
        }
    }

}
