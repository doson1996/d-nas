package com.ds.nas.hc.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ds
 * @date 2022/12/4
 * @description
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "com.ds.nas.hc.app",
        "com.ds.nas.hc.dao",
        "com.ds.nas.hc.service"})
public class HcApplication {
    public static void main(String[] args) {
        SpringApplication.run(HcApplication.class, args);
    }
}


