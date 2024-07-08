package com.ds.nas.nat.app.dubbo;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.*;

import java.util.Map;

/**
 * @author ds
 * @date 2024/1/29
 * @description
 */
@Slf4j
public class LogFilter extends BizFilter {

    @Override
    public void before(Invocation invocation) {
        Object[] arguments = invocation.getArguments();
        Map<String, String> attachments = invocation.getAttachments();
        invocation.setAttachment("app", "nas");
        log.info("before, attachments = {}", attachments);
    }

    @Override
    public void after(Result result, Invocation invocation) {
        String app = invocation.getAttachment("app", "default-nas");
        Object rep = result.getValue();
        log.info("after, app = {}, result = {}", app, rep);
    }

}
