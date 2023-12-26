package com.ds.nas.nat.app.boot;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author ds
 * @date 2023/12/26
 * @description
 */
public class MyApplicationEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param context the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MyApplicationEvent(ConfigurableApplicationContext context) {
        super(context);
    }
}
