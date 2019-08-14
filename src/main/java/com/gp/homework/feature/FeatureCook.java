package com.gp.homework.feature;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Create by Wayne on 2019/4/25
 * @author Wayne
 */
public class FeatureCook {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long stattTime = System.currentTimeMillis() ;

        Callable<FeatureChuJv> netShopping = ()->{

            System.out.println();
            System.out.println();
            Thread.sleep(5000);
            System.out.println();

            return new FeatureChuJv() ;
        } ;


        FutureTask<FeatureChuJv> task = new FutureTask<>(netShopping) ;
        new Thread(task).start();


        FeatureShiCai shicai = new FeatureShiCai() ;
        System.out.println("第二步 食材到位");

        Thread.sleep(2000);
        System.out.println("第三步 开始做菜!!!");

        long endTime = System.currentTimeMillis() ;

        cook(task.get(),shicai);

        System.out.println("花费时间："+(endTime-stattTime));

    }

    private static void cook(FeatureChuJv chujv, FeatureShiCai shicai){}
    class NetShopping{}

    private static class FeatureChuJv{}
    private static class FeatureShiCai{}

}
