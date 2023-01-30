package com.ds.nas.lib.cache.config;

import org.springframework.context.annotation.ComponentScan;

/**
 * @author ds
 * @date 2023/1/23
 * @description 未引入spring-boot-starter-web
 */
@Deprecated
@ComponentScan(basePackages = {"com.ds.lib.cache"})
public class CacheAutoConfiguration {
}
