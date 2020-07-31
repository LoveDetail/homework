package com.gp.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Create by Wayne on 2020/7/25
 */

@Component
public class KafkaBean {

    @Autowired
    private KafkaTemplate kafkaTemplate ;


    public String send(){
        kafkaTemplate.send("someTopic","key","message") ;
        return null ;
    }




    @KafkaListener(topics = "someTopic")
    public void processMessage(String content) {


        kafkaTemplate.executeInTransaction(new KafkaOperations.OperationsCallback() {
            @Override
            public Object doInOperations(KafkaOperations kafkaOperations) {

                return null;
            }
        }) ;

        // ...
    }




}
