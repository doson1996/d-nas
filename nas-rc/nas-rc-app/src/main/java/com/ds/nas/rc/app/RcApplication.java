package com.ds.nas.rc.app;

import com.ds.starter.aegis.EnableAegis;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ds
 * @date 2024/2/28
 * @description
 */
@Slf4j
@EnableDubbo
@EnableAegis
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "com.ds.nas.lib",
        "com.ds.nas.rc.app",
        "com.ds.nas.rc.dao",
        "com.ds.nas.rc.service"})
public class RcApplication {
    public static void main(String[] args) {
        SpringApplication.run(RcApplication.class, args);
    }
}
