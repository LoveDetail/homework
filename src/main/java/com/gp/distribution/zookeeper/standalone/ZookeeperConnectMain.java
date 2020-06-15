package com.gp.distribution.zookeeper.standalone;

import cn.hutool.core.util.StrUtil;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Create by Wayne on 2020/6/12
 */
public class ZookeeperConnectMain {

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        final CountDownLatch countDownLatch=new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("192.168.2.76:2181", 4000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
                    countDownLatch.countDown();
                }
            }
        }) ;

        countDownLatch.await();

        System.out.println(StrUtil.format("************{}",zooKeeper.getState()));//CONNECTING


        //增加节点
        zooKeeper.create("/zk-persis-mic","0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        Thread.sleep(1000);
        Stat stat=new Stat();

        //得到当前节点的值
        byte[] bytes=zooKeeper.getData("/zk-persis-mic",null,stat);
        System.out.println(new String(bytes));


        //修改节点值
        zooKeeper.setData("/zk-persis-mic","1".getBytes(),stat.getVersion());

        //得到当前节点的值
        byte[] bytes1=zooKeeper.getData("/zk-persis-mic",null,stat);
        System.out.println(new String(bytes1));

//        zooKeeper.delete("/zk-persismic",stat.getVersion());


        zooKeeper.close();

        System.in.read();
    }
}
