package com.ds.nas.hc.app.aspect;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ds.nas.hc.dao.domain.HcRequestLog;
import com.ds.nas.lib.common.base.db.DBUtils;
import com.ds.nas.lib.common.constant.MqTopic;
import com.ds.nas.lib.mq.kafka.KafkaUtils;
import com.ds.nas.lib.mq.producer.Producer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ds
 * @date 2023/1/22
 * @description 日志记录
 */
@Slf4j
@Order(50)
@Aspect
@Component
public class LogAspect {

    @Resource
    private HttpServletRequest request;

    @Resource
    private KafkaUtils kafkaUtils;

    @Resource
    @Qualifier("kafkaProducer")
    private Producer producer;

    @Pointcut("execution(* com.ds.nas.hc.app.controller.*Controller.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String path = request.getServletPath();
        Object requestData = joinPoint.getArgs()[0];
        long start = System.currentTimeMillis();
        //todo 有全局异常处理时{@see GlobalExceptionHandler} 执行中抛出异常会导致不往下走
        Object responseData = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log2Kafka(path, requestData, responseData, executionTime);
        return responseData;
    }

    /**
     * 记录日志到kafka
     *
     * @param path          请求路径
     * @param requestData   请求参数
     * @param responseData  响应参数
     * @param executionTime 执行时间
     */
    public void log2Kafka(String path, Object requestData, Object responseData, Long executionTime) {
        try {
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(responseData));
            String code = jsonObject.getString("code");
            HcRequestLog hcLog = new HcRequestLog();
            hcLog.setPath(path);
            hcLog.setReturnCode(code);
            hcLog.setRequestData(JSON.toJSONString(requestData));
            hcLog.setResponseData(JSON.toJSONString(responseData));
            hcLog.setExecutionTime(executionTime);
            DBUtils.onCreate(hcLog);
            //  producer.send(MqTopic.HC_REQUEST_LOG_TOPIC, JSON.toJSONString(log));
            kafkaUtils.send(MqTopic.HC_REQUEST_LOG_TOPIC, JSON.toJSONString(hcLog));
        } catch (Exception e) {
            log.error("记录日志异常: {}", e.getMessage());
        }
    }

}
