package com.gp.homework.feature;

import java.util.concurrent.TimeUnit;

/**
 * Create by Wayne on 2019/4/25
 */
public class CommonCook {

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis() ;


        System.out.println("去超市购买食材，花费时间：2 s");
        Thread.sleep(2000);

        Shoping shopingThread = new Shoping() ;
        shopingThread.start();
        shopingThread.join() ;

        long end = doCook(shopingThread.chujv,new ShiCai()) ;
        System.out.println("完成，一共用时"+(end - start)+"ms");
    }

    private static long doCook(ChuJv chujv, ShiCai shiCai) {
        return System.currentTimeMillis() ;
    }

    static class Shoping extends  Thread{
        private ChuJv chujv ;

        @Override
        public void run() {
            System.out.println("第一步 下单");
            System.out.println("第二步 配送");

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            chujv = new ChuJv() ;
        }
    }

    private static class ChuJv{}
    private static class ShiCai{}




}
