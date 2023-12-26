package com.ds.nas.nat.app.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

/**
 * @author ds
 * @date 2023/12/26
 * @description
 */
@Slf4j
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {
    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        log.info("myApplicationListener...");
    }
}
