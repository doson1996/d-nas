package com.ds.nas.cloud.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ds
 * @date 2023/3/13
 * @description 批量调度平台
 */
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.ds.nas.hc.api.fegin"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }
}
