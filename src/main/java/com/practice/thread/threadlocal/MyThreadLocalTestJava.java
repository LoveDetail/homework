package com.practice.thread.threadlocal;

import cn.hutool.core.util.StrUtil;

import java.util.concurrent.TimeUnit;

/**
 * Create by Wayne on 2020/4/20
 */
public class MyThreadLocalTestJava {

    private static final ThreadLocal<String> LOCAL = new ThreadLocal<>() ;

    public static void main(String[] args) {

        LOCAL.set("main_Thread");
        System.out.println(StrUtil.format("the main thread String is {}",LOCAL.get()));

        new Thread(()->{
            System.out.println(StrUtil.format("the main thread String is {}",LOCAL.get()));

            LOCAL.set("sub_Thread");
            System.out.println(StrUtil.format("the sub_Thread String is {}",LOCAL.get()));

            new Thread(()->{
                System.out.println(StrUtil.format("the sub_Thread String is {}",LOCAL.get()));

                LOCAL.set("Third_Thread");
                System.out.println(StrUtil.format("the Third_Thread String is {}",LOCAL.get()));
            }).start();

        }).start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("11111111");
    }

}
