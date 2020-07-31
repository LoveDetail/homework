package com.gp.homework.mythread;

import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * Create by Wayne on 2020/7/31
 * 两个现成轮流打印奇数和偶数
 *
 */
public class MyNotifyDemo {

    private static int number = 1 ;
    private static boolean isOdd = true ;


    public static void main(String[] args) {
        OddEventNumber o = new OddEventNumber() ;

        new Thread(o :: odd).start();
        new Thread(o :: even).start();
    }

   static class OddEventNumber {


        @SneakyThrows
        public synchronized void odd()  {

            while (true){
                if(isOdd){
                    System.out.println(StrUtil.format("threadName::::{} ===> {}",Thread.currentThread().getName(),number++));
                    TimeUnit.SECONDS.sleep(1);
                    isOdd = false ;
                    notify() ;
                }
                else{
                  wait();
                }
            }
        }

        public synchronized void even() {
            while (true){
                if(!isOdd){
                    System.out.println(StrUtil.format("threadName::::{} ===> {}",Thread.currentThread().getName(),number++));
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    isOdd = true ;
                    notify() ;
                }
                else{
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }
}



