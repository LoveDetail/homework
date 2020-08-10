package com.gp.homework.controller.aop;

import cn.hutool.core.thread.ThreadUtil;
import com.common.annotation.DistributionLockListenor;
import com.common.annotation.NoRepeatSubmit;
import com.gp.homework.common.util.RedisUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Create by Wayne on 2020/8/10
 */
@RestController
@RequestMapping("/aopController")
public class MyTestAOPController {

    @Autowired
    RedisUtil redisUtil ;

    @Autowired
    RestTemplate restTemplate ;


    @SneakyThrows
    @ResponseBody
    @GetMapping("/tokenKey")
    @DistributionLockListenor(functionName="myLockSalesOrder")
    public String distrbuiltKey(){
        return "" ;
    }


    /**
     * 分布式锁，测试重复提交问题
     * @return
     */
    @NoRepeatSubmit
    @ResponseBody
    @GetMapping("/token2")
    public String tokenTest(){
        System.out.println("I'm a Aspect !!");
        return "fine" ;
    }


    /**
     * 分布式锁，测试重复提交问题
     * @return
     */
    @GetMapping("/token")
    public String token(){

        System.out.println("执行多线程测试");
        String url = "http://localhost:8080/aopController/tokenKey" ;
        CountDownLatch countDownLatch = new CountDownLatch(1) ;

        for(int i=0; i<10; i++){
            String userId = "wayne"+i ;

            ThreadUtil.execute(()->{
                try {
                    countDownLatch.await();
//                    System.out.println("Thread:"+Thread.currentThread().getName()+
//                            ",time:"+System.currentTimeMillis());

//                    ResponseEntity<String> response = restTemplate.postForEntity(url,buildRequest(userId),String.class) ;

                    ResponseEntity<String> response = restTemplate.getForEntity(url,String.class) ;

//                    System.out.println("Thread:"+Thread.currentThread().getName()+","+response.getBody());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.countDown();

        return "token" ;
    }














    @RequestMapping(value = "/Curry")
    public void Curry(){
        System.out.println("库里上场打球了！！");
    }

    @RequestMapping(value = "/Harden")
    public void Harden(){
        System.out.println("哈登上场打球了！！");
    }

    @RequestMapping(value = "/Antetokounmpo")
    public void Antetokounmpo(){
        System.out.println("字母哥上场打球了！！");
    }

    @RequestMapping(value = "/Jokic")
    public void Jokic(){
        System.out.println("约基奇上场打球了！！");
    }

    @RequestMapping(value = "/Durant/{point}")
    public void Durant(@PathVariable("point")  int point){
        System.out.println("杜兰特上场打球了！！");
    }

    private HttpEntity buildRequest(String userId){
        HttpHeaders headers = new HttpHeaders() ;
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","yourToken");
        Map<String,Object> body = new HashMap<>() ;
        body.put("userId",userId) ;
        return new HttpEntity(body,headers) ;
    }

}
