package com.gp.homework.jdk.enmu;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Create by Wayne on 2020/5/12
 */
public class EnumTestJava {

    public static <T extends Enum> T getEnum(Class<T> clazz,String key){
       Enum[] m = clazz.getEnumConstants();

       for(Enum e : m){
            try {
                if(Objects.equals(clazz.getMethod("getCode").invoke(e),key))
                    return (T)e ;
            }
            catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ee) {
                ee.printStackTrace();
            }
        }
        return null ;
    }

    public static void main(String[] args) {


        BigDecimal a = new BigDecimal("0.1") ;

        BigDecimal b = new BigDecimal(0.1000).setScale(2,BigDecimal.ROUND_HALF_UP);


        BigDecimal bb = new BigDecimal(0.1) ;

        System.out.println(a.compareTo(b));

//        MyTestEnmu e = getEnum(MyTestEnmu.class,"0") ;
//        System.out.println(e.getName());
//        System.out.println(e.getCode());


    }

}
