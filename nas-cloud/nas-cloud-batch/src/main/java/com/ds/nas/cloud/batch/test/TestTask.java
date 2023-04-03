package com.ds.nas.cloud.batch.test;

import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ds
 * @date 2023/4/3
 * @description xxl-job 测试任务
 */
@Slf4j
@Component
public class TestTask {

    @XxlJob("TestTask.print")
    public void print(String arg) {
        log.info("TestTask.print " + arg);
    }

}
