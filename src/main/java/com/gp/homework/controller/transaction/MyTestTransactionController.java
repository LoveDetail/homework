package com.gp.homework.controller.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Wayne on 2020/8/4
 */
@RestController
@RequestMapping("/mytransaction")
public class MyTestTransactionController {

    @Autowired
    MyTransactionService myTransactionService ;



    /**
     * 只需要你在需要添加公共方法上面添加该注解即可。
     * 但是这么使用的话需要你将异常抛出，由spring进行去控制
     */
    @RequestMapping("/demo1")
    @Transactional
    public void demo1() throws Exception {
        myTransactionService.test1(new TransactionUser(11L)) ;
    }

    @RequestMapping("/demo2")
    @Transactional
    public void demo2(){
        myTransactionService.test2(new TransactionUser(11L)) ;
    }

    @RequestMapping("/demo3")
    public void demo3(){
        myTransactionService.test3(new TransactionUser(11L)) ;
    }

    @RequestMapping("/demo4")
    public void demo4(){
        myTransactionService.test4(new TransactionUser(1L)) ;
    }


    @RequestMapping("/demo5")
    public void demo5(){
        myTransactionService.test5(new TransactionUser(11L)) ;
    }


}
