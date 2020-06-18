package com.gp.homework.controller;

import com.gp.homework.domain.entity.TestUsers;
import com.gp.homework.domain.mapper.MyTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create by Wayne on 2020/6/2
 */

@Controller
@RequestMapping("/aaa")
public class MyTestOldController {

    @Autowired
    MyTestMapper myTestMapper  ;


    @RequestMapping("/bbb")
    @ResponseBody
    public void s(){
        System.out.println("111");
    }

    @RequestMapping("/ccc")
    @ResponseBody
    public int testInsert(){
        TestUsers users = new TestUsers() ;
        users.setNick_name("en!!!~~");
        users.setPassword("da");
        users.setUser_sex("2");
        users.setUsername("Fruit");

       myTestMapper.insetTestUsers(users) ;
        return users.getId() ;
    }





}
