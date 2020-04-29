package com.gp.homework.controller;

import cn.hutool.core.util.StrUtil;
import com.gp.homework.callback.ClientRequest;
import com.gp.homework.common.CommonTimeCache;
import com.gp.homework.event.myevent.SalesTrnasEventPusher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Create by Wayne on 2020/4/23
 */
@RestController
@RequestMapping("/test")
public class MyTestController {

    SalesTrnasEventPusher eventDemoPublish ;

    @Autowired
    public void setEventDemoPublish(SalesTrnasEventPusher eventDemoPublish) {
        this.eventDemoPublish = eventDemoPublish;
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
