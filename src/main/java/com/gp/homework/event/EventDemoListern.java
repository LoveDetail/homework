package com.gp.homework.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Create by Wayne on 2020/4/23
 */

@Component
public class EventDemoListern implements ApplicationListener<EventDemo> {
    @Override
    public void onApplicationEvent(EventDemo eventDemo) {
        System.out.println("receiver " + eventDemo.getMessage());
    }
}
