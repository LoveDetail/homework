package com.gp.homework.jdk.blockqueue;

import cn.hutool.core.thread.ThreadUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * Create by Wayne on 2020/7/30
 */

//  LinkedTransferQueue、LinkedBlockingDeque
public class MyTestBlockQueueTestJava {

    public static void main(String[] args) throws InterruptedException {
        testLinkedBlockingDeque() ;
    }


    /**
     * LinkedBlockingDeque
     * 链表结构组成的双向阻塞队列 无界	加锁	双链表
     * 多了addFirst、addLast、peekFirst、peekLast等方法，
     * 以first结尾的方法，表示插入、获取获移除双端队列的第一个元素。
     * 以last结尾的方法，表示插入、获取获移除双端队列的最后一个元素
     * 支持定时offer和poll
     * 可选容量的，在初始化时可以设置容量防止其过度膨胀，如果不设置，默认容量大小为Integer.MAX_VALUE
     */
    private static void testLinkedBlockingDeque(){
//        LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque() ;
        LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque(3) ;
//        LinkedBlockingDeque<Integer> linkedBlockingDeque = new LinkedBlockingDeque(new ArrayList()) ;

        linkedBlockingDeque.add(1) ;
        linkedBlockingDeque.add(2);
        linkedBlockingDeque.add(3);

        System.out.println(linkedBlockingDeque.peek());
        System.out.println(linkedBlockingDeque.peekFirst());
        System.out.println(linkedBlockingDeque.peekLast());


    }


    /**
     * LinkedTransferQueue 可直接传递的阻塞队列，队列空时无需入和出操作,是 LinkedBolckingQueue 和 SynchronousQueue 和合体
     * 无界	无锁(CAS)	单链表
     *
     *
     * @throws InterruptedException
     */
    private static void testLinkedTransferQueue() throws InterruptedException {
        LinkedTransferQueue linkedTransferQueue = new LinkedTransferQueue() ;

        System.out.println(linkedTransferQueue.add(1));
        System.out.println(linkedTransferQueue.offer(1));


        System.out.println(linkedTransferQueue.peek());
        System.out.println(linkedTransferQueue.take());
        System.out.println(linkedTransferQueue.poll());
        System.out.println(linkedTransferQueue.remove());



    }


    /**
     * 洗盘子队列 ,有界，无锁，cas，无存储
     */
    private static void testSynchronousQueueExample(){

        final BlockingQueue<String> synchronousQueue = new SynchronousQueue<>();

        SynchronousQueueProducer queueProducer = new SynchronousQueueProducer(synchronousQueue);
        new Thread(queueProducer).start();

        SynchronousQueueConsumer queueConsumer1 = new SynchronousQueueConsumer(synchronousQueue);
        new Thread(queueConsumer1).start();

        SynchronousQueueConsumer queueConsumer2 = new SynchronousQueueConsumer(synchronousQueue);
        new Thread(queueConsumer2).start();

    }




    /**
     * 延时队列  DelayQueue中的元素要实现Delayed接口,Delayed接口又实现了 Comparable接口  全局可重入锁来实现同步,常用于定时任务,内部使用优先级队列来存储
     * 延时队列： 无界，加锁，堆
     *
     */
    private static void testDelayQueue(){

        // 创建延时队列
        DelayQueue<Message> queue = new DelayQueue<>();

        // 添加延时消息,m1 延时3s
        Message m1 = new Message(1, "world", 3000);
        // 添加延时消息,m2 延时10s
        Message m2 = new Message(2, "hello", 10000);

        //将延时消息放到延时队列中
        queue.offer(m2);
        queue.offer(m1);

        // 启动消费线程 消费添加到延时队列中的消息，前提是任务到了延期时间
        ThreadUtil.execute(new Consumer(queue));



    }

    /**
     * PriorityBlockingQueue 一个支持优先级的无界阻塞队列，直到系统资源耗尽，  基于最小二叉堆实现
     *
     * 增加元素：
     *  1、add 如果队列未满，则加入到队列尾部，返回true，否则抛出异常[IllegalStateException Queue full]。底层基于offer方法实现，不会阻塞
     *  2、offer 如果队列未满，则加入到队列尾部，返回true,如果已满，直接返回false，不阻塞,
     *  3、put 插入元素的时候，如果队列满了就进行等待，直到队列可用
     *
     *  移除元素:
     *  1、remove 底层用到poll方法，与poll不同的是，如果没有元素，则抛出异常NoSuchElementException
     *  2、poll 检索并移除队列头部元素，有则返回，没有返回null
     *  3、take 检索并移除队列头部元素，没有则阻塞，直到可以返回，相当于阻塞式返回
     *  4、peek 检索但不移除队列头部元素，没有则返回null
     *
     */
    private static void testPriorityBlockingQueue() throws InterruptedException {

//        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue() ;
//        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue(5) ;
//        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue(5, (obj1,obj2)-> obj1.hashCode() - obj2.hashCode()) ;
        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue(5, Comparator.comparingInt(Object :: hashCode)) ;
//        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue(new ArrayList()) ;

        System.out.println(priorityBlockingQueue.add(1));
        System.out.println(priorityBlockingQueue.offer(1));


        System.out.println(priorityBlockingQueue.peek());
        System.out.println(priorityBlockingQueue.take());
        System.out.println(priorityBlockingQueue.poll());
        System.out.println(priorityBlockingQueue.remove());



    }

    /**
     * LinkedBlockingQueue 有界，加锁，单链表
     *
     * 增加元素：
     *  1、add 如果队列未满，则加入到队列尾部，返回true，否则抛出异常[IllegalStateException Queue full]。底层基于offer方法实现，不会阻塞
     *  2、offer 如果队列未满，则加入到队列尾部，返回true,如果已满，直接返回false，不阻塞,
     *  3、put 插入元素的时候，如果队列满了就进行等待，直到队列可用
     *
     *  移除元素:
     *  1、remove 底层用到poll方法，与poll不同的是，如果没有元素，则抛出异常NoSuchElementException
     *  2、poll 检索并移除队列头部元素，有则返回，没有返回null
     *  3、take 检索并移除队列头部元素，没有则阻塞，直到可以返回，相当于阻塞式返回
     *  4、peek 检索但不移除队列头部元素，没有则返回null
     */
    private static void testLinkedBlockingQueue() throws InterruptedException {
        LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>(1) ;
        System.out.println(linkedBlockingQueue.add(1));
        System.out.println(linkedBlockingQueue.offer(1));


        System.out.println(linkedBlockingQueue.peek());
        System.out.println(linkedBlockingQueue.take());
        System.out.println(linkedBlockingQueue.poll());
        System.out.println(linkedBlockingQueue.remove());
    }

    /**
     * ArrayBlockingQueue 有界，加锁，数组
     *
     * 增加元素：
     *  1、add 如果队列未满，则加入到队列尾部，返回true，否则抛出异常[IllegalStateException Queue full]。底层基于offer方法实现，不会阻塞
     *  2、offer 如果队列未满，则加入到队列尾部，返回true,如果已满，直接返回false，不阻塞,
     *  3、put 插入元素的时候，如果队列满了就进行等待，直到队列可用
     *
     *  移除元素:
     *  1、remove 底层用到poll方法，与poll不同的是，如果没有元素，则抛出异常NoSuchElementException
     *  2、poll 检索并移除队列头部元素，有则返回，没有返回null
     *  3、take 检索并移除队列头部元素，没有则阻塞，直到可以返回，相当于阻塞式返回
     *  4、peek 检索但不移除队列头部元素，没有则返回null
     */
    private static  void testArrayBlockingQueue(){

        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        System.out.println("增加值之前"+arrayBlockingQueue.size());
        for (int i = 0; i < 5; i++) {
            boolean add = arrayBlockingQueue.add(i + "");
            System.out.println(add);

        }
        System.out.println("增加值之后"+arrayBlockingQueue.size());
        System.out.println(arrayBlockingQueue.toString());

        arrayBlockingQueue.add(6+"") ;



        System.out.println("取值开始：");
        for (int i = 0; i < 5; i++) {

            System.out.println("取出的值为："+arrayBlockingQueue.peek());

        }
    }

}

@Setter
@Getter
class Message implements Delayed {

    private int id;
    private String body; // 消息内容
    private long excuteTime;// 延迟时长，这个是必须的属性因为要按照这个判断延时时长。

    public Message(int id, String body, long delayTime) {
        this.id = id;
        this.body = body;
        this.excuteTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.nanoTime();
    }

    @Override
    // 延迟任务是否到时就是按照这个方法判断如果返回的是负数则说明到期否则还没到期
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.excuteTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    // 自定义实现比较方法返回 1 0 -1三个参数
    public int compareTo(Delayed delayed) {
        Message msg = (Message) delayed;
        return Integer.valueOf(this.id) > Integer.valueOf(msg.id) ? 1 : (Integer.valueOf(this.id) < Integer.valueOf(msg.id) ? -1 : 0);
    }
}

class Consumer implements Runnable {
    // 延时队列 ,消费者从其中获取消息进行消费
    private DelayQueue<Message> queue;

    public Consumer(DelayQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message take = queue.take();
                System.out.println("消费消息id：" + take.getId() + " 消息体：" + take.getBody());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
 class SynchronousQueueProducer implements Runnable {

    protected BlockingQueue<String> blockingQueue;

    public SynchronousQueueProducer(BlockingQueue<String> queue) {
        this.blockingQueue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String data = UUID.randomUUID().toString();
                System.out.println("Put: " + data);
                blockingQueue.put(data);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

 class SynchronousQueueConsumer implements Runnable {

    protected BlockingQueue<String> blockingQueue;

    public SynchronousQueueConsumer(BlockingQueue<String> queue) {
        this.blockingQueue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String data = blockingQueue.take();
                System.out.println(Thread.currentThread().getName()
                        + " take(): " + data);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
