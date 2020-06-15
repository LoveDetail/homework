package com.gp.distribution.zookeeper.lock.zookeeper;

import java.io.IOException;

/**
 * Create by Wayne on 2020/6/15
 */
public class LockApp {

    public static void main(String[] args) throws IOException {

        ZookeeperLock distributedLock=new ZookeeperLock();
        distributedLock.tryLock() ;


//        CountDownLatch countDownLatch=new CountDownLatch(10);
//        for(int i=0;i<10;i++){
//            new Thread(()->{
//                try {
//                    countDownLatch.await();
//                    ZookeeperLock distributedLock=new ZookeeperLock();
//                    distributedLock.lock(); //获得锁
//                }
//                catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            },"Thread-"+i).start();
//            countDownLatch.countDown();
//        }
//        System.in.read();
    }
}
