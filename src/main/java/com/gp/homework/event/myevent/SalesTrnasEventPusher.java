package com.gp.homework.event.myevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Create by Wayne on 2020/4/23
 */

@Component
public class SalesTrnasEventPusher {

    ApplicationEventPublisher applicationEventPublisher ;

    @Autowired
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(String userName){
        applicationEventPublisher.publishEvent(new SalesTransEvent(this,userName));
    }


}
