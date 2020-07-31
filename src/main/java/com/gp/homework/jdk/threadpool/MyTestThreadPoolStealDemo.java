package com.gp.homework.jdk.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

/**
 * Create by Wayne on 2020/7/31
 */
public class MyTestThreadPoolStealDemo {

   static ExecutorService executorStealPool = Executors.newWorkStealingPool(8);
   static ExecutorService executorForkJoinPool = ForkJoinPool.commonPool() ;
    static ExecutorService newForkJoinPool = new ForkJoinPool() ;

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            final int taskIndex = i;
            executorForkJoinPool.execute(new Thread() {
                @Override
                public void run() {
                    System.out.println(taskIndex) ;
                }
            });

        }
    }

    private static void a(){}
}
