package com.ds.nas.hc.app;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author ds
 * @date 2022/12/4
 * @description
 */
@EnableDubbo
@EnableFeignClients
@EnableDiscoveryClient
@EnableAspectJAutoProxy
@DubboComponentScan(basePackages = {"com.ds.nas.hc.service.provider"})
@SpringBootApplication(scanBasePackages = {
        "com.ds.nas.lib.cache",
        "com.ds.nas.lib.mq",
        "com.ds.nas.hc.app",
        "com.ds.nas.hc.dao",
        "com.ds.nas.hc.service"})
public class HcApplication {

    private static final Logger log = LoggerFactory.getLogger("app");

    public static void main(String[] args) {
        log.info("HcApplication initiate...");
        SpringApplication.run(HcApplication.class, args);
    }

}


