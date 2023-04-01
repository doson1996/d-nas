package com.ds.nas.hc.app;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

    public static void main(String[] args) {
        log.info("HcApplication Starting...");
        SpringApplication.run(HcApplication.class, args);
    }

}


