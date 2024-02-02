package com.ds.nas.nat.app.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author ds
 * @date 2023/12/20
 * @description
 */
@Slf4j
public class MySpringApplicationRunListener implements SpringApplicationRunListener {

    private final SpringApplication application;

    private final String[] args;

    public MySpringApplicationRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    @Override
    public void starting() {
        log.info("starting...");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        log.info("environmentPrepared...");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("contextPrepared...");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("contextLoaded...");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        context.publishEvent(new MyApplicationEvent(context));
        AvailabilityChangeEvent.publish(context, LivenessState.CORRECT);
        log.info("started...");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        log.info("running...");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        // 项目启动失败，发送邮件通知
        log.info("failed...");
    }

}
