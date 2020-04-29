package com.gp.homework.event;

import org.springframework.context.ApplicationEvent;

/**
 * Create by Wayne on 2020/4/23
 */
public class EventDemo extends ApplicationEvent {

    private String message;
    public EventDemo(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
