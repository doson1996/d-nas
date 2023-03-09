package com.ds.nas.nat.app;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ds
 * @date 2022/12/9
 * @description
 */
@EnableDubbo
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.ds.nas.hc.api"})
@DubboComponentScan(basePackages = {"com.ds.nas.nat.service.provider"})
@SpringBootApplication(scanBasePackages = {
        "com.ds.nas.lib.cache",
        "com.ds.nas.nat.app",
        "com.ds.nas.nat.dao",
        "com.ds.nas.nat.service"})
public class NatApplication {
    public static void main(String[] args) {
        SpringApplication.run(NatApplication.class, args);
    }
}
