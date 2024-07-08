package com.ds.nas.nat.app.dubbo;

import org.apache.dubbo.rpc.*;

import java.util.Map;

/**
 * @author ds
 * @date 2024/7/5
 * @description
 */
public abstract class BizFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        before(invocation);
        Result result = invoker.invoke(invocation);
        after(result, invocation);
        return result;
    }

    public abstract void before(Invocation invocation);

    public abstract void after(Result result, Invocation invocation);

}
