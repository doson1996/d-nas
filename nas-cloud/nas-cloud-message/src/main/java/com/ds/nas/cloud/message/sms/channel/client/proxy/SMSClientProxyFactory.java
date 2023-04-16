package com.ds.nas.cloud.message.sms.channel.client.proxy;

import com.ds.nas.lib.core.proxy.BaseCglibProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ds
 * @date 2023/4/14
 * @description 短信客户端代理工厂
 */
@Slf4j
public class SMSClientProxyFactory extends BaseCglibProxy {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = method.invoke(target, objects);
        // 如果是短信发送发送，执行发送结果上送
        if ("send".equals(method.getName())) {
            try {
                // 因为有抽象父类AbstractSMSClient，所以不能直接拿到sendUp方法，需要getSuperclass()
                Method sendUp = target.getClass().getSuperclass().getDeclaredMethod("sendUp", result.getClass());
                sendUp.invoke(target, result);
            } catch (Exception e) {
                log.error("{} intercept error, ex: ",this.getClass().getSimpleName(), e);
            }
        }
        return result;
    }

}
