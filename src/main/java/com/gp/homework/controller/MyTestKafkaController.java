package com.gp.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Wayne on 2020/7/25
 */

@RestController
@RequestMapping("/kafka")
public class MyTestKafkaController {


    @Autowired
    private KafkaBean kafkaBean ;




    @RequestMapping("/send")
    public String send(){
      return kafkaBean.send() ;
    }







}
