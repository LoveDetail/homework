package com.practice.thread;

import cn.hutool.core.util.StrUtil;

/**
 * Create by Wayne on 2020/6/18
 */
public class PrintOddAndEvenMain {

    private static class Counter {
        public int value = 1;
        public boolean odd = true;
    }

    private static Counter counter = new Counter();

    private static class PrintOdd implements Runnable {
        @Override
        public void run() {
            while (counter.value <= 100) {
                synchronized(counter) {
                    //交出锁，让打印偶数的线程执行
                    if (counter.odd) {
                        System.out.println(StrUtil.format("{}===>{}: ",Thread.currentThread().getName(),counter.value));
                        counter.value++;
                        counter.odd = !counter.odd;
                        //很重要，要去唤醒打印偶数的线程
                        counter.notify();
                    } else try {
                        counter.wait();
                    } catch (InterruptedException ignored) {
                    }
                }
            }//while
        }
    }

    private static class PrintEven implements Runnable {
        @Override
        public void run() {
            while (counter.value <= 100) {
                synchronized (counter) {
                    if (counter.odd) {
                        try {
                            counter.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(StrUtil.format("{}===>{}: ",Thread.currentThread().getName(),counter.value));
                        counter.value++;
                        counter.odd = !counter.odd;
                        counter.notify();
                    }
                }//synchronized
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new PrintOdd(),"thread-Odd").start();
        new Thread(new PrintEven(),"thread-Even").start();
    }



}
