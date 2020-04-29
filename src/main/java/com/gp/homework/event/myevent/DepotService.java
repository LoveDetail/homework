package com.gp.homework.event.myevent;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

/**
 * Create by Wayne on 2020/4/23
 */
@Service
public class DepotService {

    public void depotDelp(SalesTrans salesTrans){
        System.out.println(StrUtil.format("total {} qty is out from houseware !!!",
                salesTrans.getQty()));
        return ;
    }
}
