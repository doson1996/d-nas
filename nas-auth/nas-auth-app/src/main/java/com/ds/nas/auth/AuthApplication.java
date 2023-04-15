package com.ds.nas.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ds
 * @date 2023/3/28
 * @description 认证中心
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.ds.nas.auth",
        "com.ds.nas.lib"})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
