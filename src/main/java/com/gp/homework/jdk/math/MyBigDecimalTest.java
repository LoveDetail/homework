package com.gp.homework.jdk.math;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by Wayne on 2020/5/13
 */
public class MyBigDecimalTest {

    /** 默认的数据精度 , 采用比较常用的2位 */
    private static final int DEF_SCALE = 2;

    /** 正数样本 */
    private static final List<BigDecimal> BIG_DECIMAL_STREAM = Arrays.asList(
            new BigDecimal("1.230"),
            new BigDecimal("1.231"),
            new BigDecimal("1.234"),
            new BigDecimal("1.235"),
            new BigDecimal("1.236"),
            new BigDecimal("1.239"));

    /** 负数样本 */
    private static final List<BigDecimal> _BIG_DECIMAL_STREAM = BIG_DECIMAL_STREAM.stream().map(BigDecimal.ZERO::subtract).collect(Collectors.toList());

    public static void main(String[] args) throws Exception {
//        ROUND_CEILING();//向上进位
//        ROUND_FLOOR();//向下舍位

//        ROUND_HALF_UP();//四舍五入
//        ROUND_HALF_DOWN();//五舍六入

//        ROUND_UP();//绝对值向上进位
//        ROUND_DOWN();//绝对值向下舍位

//        ROUND_HALF_EVEN();//银行家算法 四舍六入 五看前数 奇进偶舍
        ROUND_UNNECESSARY();//不舍位 , 精度位数小于小数位数会抛出异常
    }

    /**
     * BigDecimal.ROUND_CEILING 向上进位 .
     *      若当前精度位后一位数字不为0 , 则向上进位
     *      数字更大
     */
    private static void ROUND_CEILING() {
        BIG_DECIMAL_STREAM.
                forEach(bigDecimal -> System.out.println(" " + bigDecimal + " > ROUND_CEILING >  " + bigDecimal.setScale(DEF_SCALE, BigDecimal.ROUND_CEILING)));

        System.out.println();
        _BIG_DECIMAL_STREAM.forEach(bigDecimal -> System.out.println(bigDecimal + " > ROUND_CEILING > " + bigDecimal.setScale(DEF_SCALE, BigDecimal.ROUND_CEILING)));
    }

    /**
     * BigDecimal.ROUND_FLOOR 向下舍位 .
     *      若当前精度位后一位数字不为0 , 则向下舍位
     *      数字更小
     */
    private static void ROUND_FLOOR() {
        BIG_DECIMAL_STREAM.forEach(bigDecimal -> System.out.println(" " + bigDecimal + " > ROUND_FLOOR >  " + bigDecimal.setScale(DEF_SCALE, BigDecimal.ROUND_FLOOR)));
        System.out.println();
        _BIG_DECIMAL_STREAM.forEach(bigDecimal -> System.out.println(bigDecimal + " > ROUND_FLOOR > " + bigDecimal.setScale(DEF_SCALE, BigDecimal.ROUND_FLOOR)));
    }

    /**
     * BigDecimal.ROUND_HALF_UP , 4舍5入 .
     *      正数4舍5进 , 负数4舍5退(-1.234 -> -1.23 ; -1.235 -> -1.24)
     */
    public static void ROUND_HALF_UP() {
        BIG_DECIMAL_STREAM.forEach(bigDecimal -> System.out.println(" " + bigDecimal + " > ROUND_HALF_UP >  " + bigDecimal.setScale(DEF_SCALE, BigDecimal.ROUND_HALF_UP)));
        System.out.println();
        _BIG_DECIMAL_STREAM.forEach(bigDecimal -> System.out.println(bigDecimal + " > ROUND_HALF_UP > " + bigDecimal.setScale(DEF_SCALE, BigDecimal.ROUND_HALF_UP)));
    }

    /**
     * BigDecimal.ROUND_HALF_DOWN , 5舍6入
     *      正数5舍6进 , 负数5舍6退(-1.235 -> -1.23 ; -1.236 -> -1.24)
     */
    public static void ROUND_HALF_DOWN() {
        BIG_DECIMAL_STREAM.forEach(bigDecimal -> System.out.println(" " + bigDecimal + " > ROUND_HALF_DOWN >  " + bigDecimal.setScale(DEF_SCALE, BigDecimal.ROUND_HALF_DOWN)));
        System.out.println();
        _BIG_DECIMAL_STREAM.forEach(bigDecimal -> System.out.println(bigDecimal + " > ROUND_HALF_DOWN > " + bigDecimal.setScale(DEF_SCALE, BigDecimal.ROUND_HALF_DOWN)));
    }

    /**
     * BigDecimal.ROUND_HALF_EVEN , 银行家算法 . 4舍6入 , 5看前数  , 奇进偶舍
     *      正数4舍6进 , 5看前一位数字 , 若为奇数则进位 , 若为偶数则舍位
     *      负数5舍6退(-1.235 -> -1.23 ; -1.236 -> -1.24)
     */
    public static void ROUND_HALF_EVEN() {
        BIG_DECIMAL_STREAM.forEach(bigDecimal -> System.out.println(" " + bigDecimal + " > ROUND_HALF_EVEN >  " + bigDecimal.setScale(DEF_SCALE, BigDecimal.ROUND_HALF_EVEN)));
        System.out.println();
        _BIG_DECIMAL_STREAM.forEach(bigDecimal -> System.out.println(bigDecimal + " > ROUND_HALF_EVEN > " + bigDecimal.setScale(DEF_SCALE, BigDecimal.ROUND_HALF_EVEN)));
        System.out.println();
        BigDecimal bd = new BigDecimal("0.01");
        BIG_DECIMAL_STREAM.forEach(bigDecimal -> System.out.println(" " + bigDecimal.add(bd) + " > ROUND_HALF_EVEN >  " + bigDecimal.add(bd).setScale(DEF_SCALE, BigDecimal.ROUND_HALF_EVEN)));
        System.out.println();
        _BIG_DECIMAL_STREAM.forEach(bigDecimal -> System.out.println(bigDecimal.add(bd) + " > ROUND_HALF_EVEN > " + bigDecimal.add(bd).setScale(DEF_SCALE, BigDecimal.ROUND_HALF_EVEN)));
    }

    /**
     * BigDecimal.ROUND_UP , 绝对值向上进位 .
     *      若当前精度位后一位数字不为0 , 则绝对值向上进位
     *      正数更大 , 负数更小
     */
    public static void ROUND_UP() {
        BIG_DECIMAL_STREAM.forEach(bigDecimal -> System.out.println(" " + bigDecimal + " > ROUND_UP >  " + bigDecimal.setScale(DEF_SCALE, BigDecimal.ROUND_UP)));
        System.out.println();
        _BIG_DECIMAL_STREAM.forEach(bigDecimal -> System.out.println(bigDecimal + " > ROUND_UP > " + bigDecimal.setScale(DEF_SCALE, BigDecimal.ROUND_UP)));
    }

    /**
     * BigDecimal.ROUND_DOWN , 绝对值向下舍位 .
     *      若当前精度位后一位数字不为0 , 则绝对值向下舍位
     *      正数更小 , 负数更大
     */
    public static void ROUND_DOWN() {
        BIG_DECIMAL_STREAM.forEach(bigDecimal -> System.out.println(" " + bigDecimal + " > ROUND_DOWN >  " + bigDecimal.setScale(DEF_SCALE, BigDecimal.ROUND_DOWN)));
        System.out.println();
        _BIG_DECIMAL_STREAM.forEach(bigDecimal -> System.out.println(bigDecimal + " > ROUND_DOWN > " + bigDecimal.setScale(DEF_SCALE, BigDecimal.ROUND_DOWN)));
    }

    /**
     * BigDecimal.ROUND_UNNECESSARY , 不舍位 . 精度位数小于小数位数会抛出异常
     */
    public static void ROUND_UNNECESSARY() {
        BIG_DECIMAL_STREAM
                .forEach(bigDecimal -> System.out.println(" " + bigDecimal + " > UNNECESSARY >  " + bigDecimal.setScale(3, BigDecimal.ROUND_UNNECESSARY)));


        System.out.println();
        _BIG_DECIMAL_STREAM.forEach(bigDecimal -> System.out.println(bigDecimal + " > UNNECESSARY > " + bigDecimal.setScale(3, BigDecimal.ROUND_UNNECESSARY)));
    }

}
