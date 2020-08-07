package com.gp.homework.controller;

import com.common.annotation.NoRepeatSubmit;
import com.gp.homework.domain.entity.SimpleLocalPerson;
import com.gp.homework.domain.entity.TestUsers;
import com.gp.homework.domain.mapper.MyTestLocalDateTimeMapper;
import com.gp.homework.domain.mapper.MyTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Create by Wayne on 2020/6/2
 */

@Controller
@RequestMapping("/aaa")
public class MyTestOldController {

    @Autowired
    MyTestMapper myTestMapper  ;

    @Autowired
    MyTestLocalDateTimeMapper myTestLocalDateTimeMapper ;

    @RequestMapping("/person")
    @ResponseBody
    public List<SimpleLocalPerson> queryPerson(){

        List<SimpleLocalPerson> list = myTestLocalDateTimeMapper.queryPerson() ;
        return list ;
    }

    @RequestMapping("/insertPerson")
    @ResponseBody
    @NoRepeatSubmit
    public int insertSimplePerson(SimpleLocalPerson person){
        System.out.println(person);
        return myTestLocalDateTimeMapper.insertPerson(person) ;

    }

    @RequestMapping("/bbb")
    @ResponseBody
    @NoRepeatSubmit
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
