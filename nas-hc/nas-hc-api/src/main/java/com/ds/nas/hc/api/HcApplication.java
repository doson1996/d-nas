package com.ds.nas.hc.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ds
 * @date 2022/12/4
 * @description
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "com.ds.nas.hc.api",
        "com.ds.nas.hc.service",
        "com.ds.nas.hc.dao"})
public class HcApplication {
    public static void main(String[] args) {
        SpringApplication.run(HcApplication.class, args);
    }
}


