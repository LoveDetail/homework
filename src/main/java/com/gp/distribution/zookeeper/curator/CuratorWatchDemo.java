package com.gp.distribution.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Create by Wayne on 2020/6/12
 */
public class CuratorWatchDemo {

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.
                builder().connectString("192.168.2.76:2181").
                retryPolicy(new ExponentialBackoffRetry(1000,3)).
                sessionTimeoutMs(4000).namespace("wayne").build() ;

        curatorFramework.start()  ;

//当前节点的创建和删除事件监听
//        addListenerWithNodeCache(curatorFramework,"/mic");

        //子节点的增加、修改、删除的事件监听
//        addListenerWithPathChildCache(curatorFramework,"/mic");

        curatorFramework.create().creatingParentsIfNeeded().
                withMode(CreateMode.PERSISTENT).
                forPath("/node1","1".getBytes());

        //综合节点监听事件
        addListenerWithTreeCache(curatorFramework,"/node1");
        System.in.read();
    }

    public static void addListenerWithTreeCache(CuratorFramework curatorFramework,String path) throws Exception {
        TreeCache treeCache = new TreeCache(curatorFramework,path);

        TreeCacheListener treeCacheListener=new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                System.out.println(event.getType()+"->"+event.getData().getPath());
            }
        };

        treeCache.getListenable().addListener(treeCacheListener);
        treeCache.start();
    }

    /**
     * PathChildCache 监听一个节点下子节点的创建、删除、更新
     * NodeCache  监听一个节点的更新和创建事件
     * TreeCache  综合PatchChildCache和NodeCache的特性
     */

    public static void addListenerWithPathChildCache(CuratorFramework curatorFramework,String path) throws Exception {
        PathChildrenCache pathChildrenCache=new PathChildrenCache(curatorFramework,path,true);

        PathChildrenCacheListener pathChildrenCacheListener=new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("Receive Event:"+event.getType());
            }
        };

        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start(PathChildrenCache.StartMode.NORMAL);

    }


    public static void addListenerWithNodeCache(CuratorFramework curatorFramework,String path) throws Exception {
        final NodeCache nodeCache=new NodeCache(curatorFramework,path,false);
        NodeCacheListener nodeCacheListener=new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("Receive Event:"+nodeCache.getCurrentData().getPath());
            }
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();
    }
}
