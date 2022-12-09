package com.ds.nas.nat.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ds
 * @date 2022/12/9
 * @description
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.ds.nas.nat.api", "com.ds.nas.nat.service", "com.ds.nas.nat.dao"})
public class NatApplication {
    public static void main(String[] args) {
        SpringApplication.run(NatApplication.class, args);
    }
}
