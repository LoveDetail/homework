package com.gp.distribution.zookeeper.lock.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Create by Wayne on 2020/6/15
 */
public class LockDemo {


    public static void main(String[] args) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().
                connectString("121.36.48.218:2080").sessionTimeoutMs(50000000).
                retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        curatorFramework.start();

        final InterProcessMutex lock=new InterProcessMutex(curatorFramework,"/locks");

        for(int i=0;i<10;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"->尝试竞争锁");
                try {
                    lock.acquire(); //阻塞竞争锁

                    System.out.println(Thread.currentThread().getName()+"->成功获得了锁");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    try {

                        lock.release(); //释放锁

                        System.out.println(Thread.currentThread().getName()+"->执行完成，成功释放了锁...");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            },"Thread-"+i).start();
        }
    }

}
