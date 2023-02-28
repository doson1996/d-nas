package com.ds.nas.cloud.canal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ds
 * @date 2023/2/28
 * @description canal
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.ds.nas.lib","com.ds.nas.cloud.canal"})
public class CanalApplication {
    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class, args);
    }
}
