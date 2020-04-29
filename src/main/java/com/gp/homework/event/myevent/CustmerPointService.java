package com.gp.homework.event.myevent;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

/**
 * Create by Wayne on 2020/4/23
 */
@Service
public class CustmerPointService {

    public void service(SalesTrans salesTrans){

        System.out.println(StrUtil.format("user {} price ${} and point is {}",
                salesTrans.getName(),
                salesTrans.getPrice(),
                salesTrans.getTotleAmt()));

        return ;
    }
}
