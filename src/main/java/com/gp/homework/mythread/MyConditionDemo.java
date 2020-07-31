package com.gp.homework.mythread;

import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Create by Wayne on 2020/7/31
 */
public class MyConditionDemo {

    private static ReentrantLock lock = new ReentrantLock();
    private static int number ;

    public static void main(String[] args) {
        new Thread(OddEvenNumber :: odd,"Thread-oodd").start();
        new Thread(OddEvenNumber :: even,"Thread-even").start();
    }

    private static boolean isOdd(int number){
        return (number & 1) == 1;
    }

    static class OddEvenNumber{

        static Condition condition = lock.newCondition();

        @SneakyThrows
        public static void odd(){
            for(;;){
            lock.lock();
                try{
                    if(isOdd(number)){
                        System.out.println(StrUtil.format("threadName::::{} =====>{}", Thread.currentThread().getName(), number++));
                        TimeUnit.SECONDS.sleep(1);
                        condition.signal();
                    }
                        condition.await() ;
                }
                finally {
                    lock.unlock();
                }
            }
        }


        @SneakyThrows
        public static void even(){

            for(;;){
               try{
                   lock.lock();
                   if(!isOdd(number)){
                       System.out.println(StrUtil.format("threadName::::{} =====>{}", Thread.currentThread().getName(), number++));
                       TimeUnit.SECONDS.sleep(1);
                       condition.signal();
                   }
                       condition.await();
               }
               finally {
                   lock.unlock();
               }
            }
        }
    }


}
