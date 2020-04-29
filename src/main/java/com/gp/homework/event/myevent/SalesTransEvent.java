package com.gp.homework.event.myevent;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Create by Wayne on 2020/4/23
 */
public class SalesTransEvent extends ApplicationEvent {

    private SalesTrans salesTrans ;
    public SalesTransEvent(Object source,String userName){
        super(source);
        this.salesTrans = new SalesTrans(userName) ;

        System.out.println(this.salesTrans.toString());
    }

    public SalesTrans getSalesTrans() {
        return salesTrans;
    }
}

@Getter
@Setter
@ToString
@EqualsAndHashCode
class SalesTrans{
    private static final ThreadLocalRandom random = ThreadLocalRandom.current() ;

    private BigDecimal price ;
    private BigDecimal qty ;
    private BigDecimal totleAmt ;
    private String name ;

    public SalesTrans(){}
    public SalesTrans(String userName){
        this.name = userName ;
        price = new BigDecimal(random.nextDouble()*10).setScale(2, BigDecimal.ROUND_HALF_UP) ;
        qty = new BigDecimal(random.nextInt(10)) ;
        totleAmt = price.multiply(qty).setScale(2,BigDecimal.ROUND_HALF_UP) ;
    }
}
