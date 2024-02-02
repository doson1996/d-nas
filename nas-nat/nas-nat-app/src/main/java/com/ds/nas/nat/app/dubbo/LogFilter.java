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
public class LogFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        before(invocation);
        Result result = invoker.invoke(invocation);
        after(result, invocation);
        return result;
    }

    private void before(Invocation invocation) {
        Object[] arguments = invocation.getArguments();
        Map<String, String> attachments = invocation.getAttachments();
        invocation.setAttachment("app", "nas");
        log.info("before, attachments = {}", attachments);
    }

    private void after(Result result, Invocation invocation) {
        String app = invocation.getAttachment("app", "default-nas");
        Object rep = result.getValue();
        log.info("after, app = {}, result = {}", app, rep);
    }

}
