package com.ds.nas.lib.cache.init;

import com.ds.nas.lib.cache.Cache;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ServiceLoader;

/**
 * @author ds
 * @date 2023/3/22
 * @description
 */
@Component
public class RegisterCache implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        ServiceLoader<Cache> caches = ServiceLoader.load(Cache.class);
        for (Cache cache : caches) {
            System.out.println("cache = " + cache);
        }
    }

}
