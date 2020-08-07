package com.gp.homework.controller;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.common.annotation.DistributionLockLisenor;
import com.common.annotation.NoRepeatSubmit;
import com.gp.homework.callback.ClientRequest;
import com.gp.homework.common.CommonTimeCache;
import com.gp.homework.common.util.RedisUtil;
import com.gp.homework.domain.entity.Materiel;
import com.gp.homework.domain.mapper.MyTestMapper;
import com.gp.homework.event.myevent.SalesTrnasEventPusher;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Create by Wayne on 2020/4/23
 */
@RestController
@RequestMapping("/test")
public class MyTestController {


    @Autowired
    MyTestMapper myTestMapper ;

    @Autowired
    SalesTrnasEventPusher eventDemoPublish ;


    @Autowired
    RestTemplate restTemplate ;

    @Autowired
    RedisUtil redisUtil ;

    private HttpEntity buildRequest(String userId){
        HttpHeaders headers = new HttpHeaders() ;
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","yourToken");
        Map<String,Object> body = new HashMap<>() ;
        body.put("userId",userId) ;
        return new HttpEntity(body,headers) ;
    }

    @SneakyThrows
    @ResponseBody
    @GetMapping("/tokenKey")
    @DistributionLockLisenor(functionName="myLockSalesOrder")
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
        String url = "http://localhost:8080/test/tokenKey" ;
        CountDownLatch countDownLatch = new CountDownLatch(1) ;

        for(int i=0; i<10; i++){
            String userId = "wayne"+i ;

            ThreadUtil.execute(()->{
                try {
                    countDownLatch.await();
                    System.out.println("Thread:"+Thread.currentThread().getName()+
                            ",time:"+System.currentTimeMillis());

//                    ResponseEntity<String> response = restTemplate.postForEntity(url,buildRequest(userId),String.class) ;

                    ResponseEntity<String> response = restTemplate.getForEntity(url,String.class) ;

                    System.out.println("Thread:"+Thread.currentThread().getName()+","+response.getBody());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.countDown();

        return "token" ;
    }


    @GetMapping("/m")
    public List<Materiel> MyTestMapper(){
        return myTestMapper.queryMateriel() ;
    }

    @GetMapping("/push/{userName}")
    public String push(@PathVariable("userName") String userName){

        //付款TODO

        eventDemoPublish.publish(userName);
        return "" ;
    }
    @GetMapping("/p")
    public void conrol(){
        ClientRequest request = new ClientRequest("1001100","召测") ;
        CommonTimeCache.TIMED_CACHE.remove(request);
    }

    @GetMapping("/s")
    public void start(){
        ClientRequest request = new ClientRequest("1001100","召测") ;

        //第一次召测
        System.out.println(StrUtil.format("第{}次召测",1));
        CommonTimeCache.TIMED_CACHE.put(request,System.currentTimeMillis());
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i=2; i<=3; i++){
            try {
                //等待10秒
                if(CommonTimeCache.TIMED_CACHE.get(request) != null) {
                    System.out.println(StrUtil.format("第{}次召测",i));
                    CommonTimeCache.TIMED_CACHE.put(request,System.currentTimeMillis());
                    TimeUnit.SECONDS.sleep(10);
                }

                //如果不是null，表示召测完成，
                else {
                    System.out.println(StrUtil.format("召测完成"));
                    return ;
                }

            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(StrUtil.format("召测失败"));

    }

}
