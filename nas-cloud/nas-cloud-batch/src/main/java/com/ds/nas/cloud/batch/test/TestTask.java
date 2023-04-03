package com.ds.nas.cloud.batch.test;

import com.xxl.job.core.context.XxlJobHelper;
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
    public void print() {
        // 单个参数，多个参数用分隔符，然后split
        String param;
        // 写调度中心日志内容
        String adminLog = null;
        try {
            param = XxlJobHelper.getJobParam();
            log.info("TestTask.print , param: {}", param);
            // todo 业务逻辑

            adminLog = "TestTask.print execute Success...";
            // 设置任务结果
            XxlJobHelper.handleSuccess();
        } catch (Exception e) {
            log.error("TestTask.print execute Exception...");
            adminLog = "TestTask.print execute Exception...";
            // 设置任务结果
            XxlJobHelper.handleFail();
        } finally {
            //写日志到调度中心日志中
            XxlJobHelper.log(adminLog);
        }

    }

}
