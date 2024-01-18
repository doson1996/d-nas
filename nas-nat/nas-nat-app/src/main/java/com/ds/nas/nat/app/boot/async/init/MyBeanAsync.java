package com.ds.nas.nat.app.boot.async.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author ds
 * @date 2024/1/17
 * @description
 */
@Slf4j
@AsyncInit
@Component
public class MyBeanAsync implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("myBean async afterPropertiesSet start...");
        TimeUnit.SECONDS.sleep(10);
        log.info("myBean async afterPropertiesSet end...");
    }

}
