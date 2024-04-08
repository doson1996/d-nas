package com.ds.nas.nat.app;

import com.ds.nas.lib.core.request.check.annotion.EnableRequestCheck;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@EnableRequestCheck
@EnableDubbo
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.ds.nas.hc.api"})
@DubboComponentScan(basePackages = {"com.ds.nas.nat.service.provider"})
@SpringBootApplication(scanBasePackages = {
        "com.ds.nas.lib",
        "com.ds.nas.nat.app",
        "com.ds.nas.nat.dao",
        "com.ds.nas.nat.service"})
public class NatApplication {
    public static void main(String[] args) {
        log.info("NatApplication Starting...");
        SpringApplication.run(NatApplication.class, args);
        log.info("NatApplication Started...");
    }
}
