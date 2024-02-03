package com.ds.nas.nat.dao.plugin;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * @author ds
 * @date 2024/1/29
 * @description
 */
@Intercepts({
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}),
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
})
@Slf4j
public class MyPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object res = invocation.proceed();
        long endTime = System.currentTimeMillis();
        log(invocation, startTime, endTime);
        return res;
    }

    /**
     * 记录执行时长
     *
     * @param invocation
     * @param startTime
     * @param endTime
     */
    @SneakyThrows
    private void log(Invocation invocation, long startTime, long endTime) {
        String id = ((MappedStatement) invocation.getArgs()[0]).getId();
        log.info("{} exec const {}ms}", id, (endTime - startTime));
    }

}
