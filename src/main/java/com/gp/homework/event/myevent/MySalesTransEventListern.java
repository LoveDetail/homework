package com.gp.homework.event.myevent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create by Wayne on 2020/4/23
 */
@Component
public class MySalesTransEventListern implements ApplicationListener<SalesTransEvent> {

    private static ExecutorService pool = Executors.newFixedThreadPool(3) ;

    CostService costService ;
    CustmerPointService custmerPointService ;
    DepotService depotService ;

    public MySalesTransEventListern(CostService costService, CustmerPointService custmerPointService, DepotService depotService) {
        this.costService = costService;
        this.custmerPointService = custmerPointService;
        this.depotService = depotService;
    }


    @Override
    public void onApplicationEvent(SalesTransEvent salesTransEvent) {
        pool.execute(()->costService.cost(salesTransEvent.getSalesTrans())) ;
        pool.execute(()->custmerPointService.service(salesTransEvent.getSalesTrans())) ;
        pool.execute(()->depotService.depotDelp(salesTransEvent.getSalesTrans())) ;
    }

}
