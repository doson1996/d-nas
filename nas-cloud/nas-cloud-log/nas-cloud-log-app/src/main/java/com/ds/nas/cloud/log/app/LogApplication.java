package com.ds.nas.cloud.log.app;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ds
 * @date 2023/4/6
 * @description 日志中心
 */
@EnableDubbo
@EnableDiscoveryClient
@DubboComponentScan(basePackages = {"com.ds.nas.cloud.log.service.provider"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},
        scanBasePackages = {
                "com.ds.nas.lib",
                "com.ds.nas.cloud.log.app",
                "com.ds.nas.cloud.log.dao",
                "com.ds.nas.cloud.log.service"})
public class LogApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class, args);
    }
}
