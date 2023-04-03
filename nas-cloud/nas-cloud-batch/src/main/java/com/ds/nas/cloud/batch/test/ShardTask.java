package com.ds.nas.cloud.batch.test;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ds
 * @date 2023/4/3
 * @description 分片任务
 */
@Slf4j
@Component
public class ShardTask {

    @XxlJob("ShardTask.sendMail")
    public void sendMail() {
        long start = System.currentTimeMillis();
        // 分片总数
        int shardTotal = XxlJobHelper.getShardTotal();
        // 分片索引
        int shardIndex = XxlJobHelper.getShardIndex();
        log.info("ShardTask.sendMail start... shardTotal：{}, shardIndex：{}", shardTotal, shardIndex);
        List<String> mailAddrList = getMailAddrList();
        try {
            for (int i = 0; i < mailAddrList.size(); i++) {
                // 取模
                if (i % shardTotal == shardIndex) {
                    Thread.sleep(10);
                    log.info("send mail {}", mailAddrList.get(i));
                }
            }
            // 设置任务结果
            XxlJobHelper.handleSuccess();
        } catch (Exception e) {
            log.error("ShardTask.print execute Exception...");
            // 设置任务结果
            XxlJobHelper.handleFail();
        } finally {
            log.info("send mail cost {} ms.", (System.currentTimeMillis() - start));
        }
    }

    /**
     * 模拟业务数据
     * @return
     */
    public List<String> getMailAddrList() {
        List<String> mailList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            mailList.add("12345" + i + "@qq.com");
        }
        return mailList;
    }

}
