package com.gp.homework.jdk.jdk8;

import cn.hutool.core.util.StrUtil;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * JDK8新特性里提供了3个时间类：LocalDate、LocalTime、LocalDateTime
 *
 * 在项目开发中，已经需要对Date类型进行格式，否则可读性很差，
 * 格式化Date类型要使用SimpleDateFormat，但SimpleDateFormat是线程不安全的。
 */
public class LocalDateTimeTestJava {


    public static void main(String[] args) {
        testLocalDate();
        System.out.println("==========================================================================================================================================================================================");

        testLocalTime();
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");

        testLocalDateTime() ;

        System.out.println("***********************************************************************************************************************************************************************************************");
        testInstant();

        System.out.println("````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````");
        modifyLocalJdk8T() ;

        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        dateCompare() ;

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        dateAndLocalDateTime() ;


    }


    /**
     * Date与LocalDateTime转换
     *
     * LocalDateTime：Date有的我都有，Date没有的我也有，
     */
    private static void dateAndLocalDateTime(){

        //LocalDateTime转毫秒时间戳
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = LocalDateTime.now().atZone(zoneId).toInstant() ;
        System.out.println(StrUtil.format("LocalDateTime.now().atZone(zoneId).toInstant() 毫秒时间戳[{}]",instant.toEpochMilli()));

        //时间戳转LocalDateTime
        instant = Instant.ofEpochMilli(System.currentTimeMillis()) ;
        System.out.println(StrUtil.format("LocalDateTime.ofInstant(instant, zone) 转LocalDateTime结果 [{}]",LocalDateTime.ofInstant(instant, zoneId)));

        //Date转LocalDateTime
        Instant dateInstant = Calendar.getInstance().getTime().toInstant();
        System.out.println(StrUtil.format("instant.atZone(zoneId).toLocalDateTime() Date 转 LocalDateTime 结果为[{}]", dateInstant.atZone(zoneId).toLocalDateTime()));
        System.out.println(StrUtil.format("LocalDateTime.ofInstant(instant,zoneId) Date 转 LocalDateTime 结果为[{}]", LocalDateTime.ofInstant(instant,zoneId)));

        //LocalDateTime转Date
        System.out.println("LocalDate 转Date 结果为"+Date.from(LocalDateTime.now().atZone(zoneId).toInstant()));

    }



    /**
     * 日期计算。
     * 比如有些时候想知道这个月的最后一天是几号、下个周末是几号，
     * 通过提供的时间和日期API可以很快得到答案 。
     * TemporalAdjusters提供的各种日期时间格式化的静态类，比如firstDayOfYear是当前日期所属年的第一天
     */
    private static void dateCompare(){
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = localDate.with(TemporalAdjusters.firstDayOfYear());

        System.out.println(StrUtil.format("localDate.with(TemporalAdjusters.firstDayOfYear()) 结果为 [{}]",localDate1));


        // 以下是格式化部分
        localDate = LocalDate.of(2019, 9, 10);
        String s1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(StrUtil.format("localDate.format(DateTimeFormatter.BASIC_ISO_DATE) 格式化为[{}]",s1));

        String s2 = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(StrUtil.format("localDate.format(DateTimeFormatter.ISO_LOCAL_DATE) 格式化为[{}]",s2));

        //自定义格式化
        DateTimeFormatter dateTimeFormatter =   DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String s3 = localDate.format(dateTimeFormatter);
        System.out.println(StrUtil.format("localDate.format(DateTimeFormatter.ofPattern(\"dd/MM/yyyy\")) 格式化为[{}]",s3));


        //解析时间。和SimpleDateFormat相比，DateTimeFormatter是线程安全的
        LocalDate decodeLocalDate = LocalDate.parse("20190910", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(StrUtil.format("LocalDate.parse(\"20190910\", DateTimeFormatter.BASIC_ISO_DATE) 结果为 [{}]",decodeLocalDate));

        LocalDate decodeLocalDate2 = LocalDate.parse("2019-09-10", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(StrUtil.format("LocalDate.parse(\"2019-09-10\", DateTimeFormatter.ISO_LOCAL_DATE) 结果为 [{}]",decodeLocalDate2));

    }


    /**
     * 修改LocalDate、LocalTime、LocalDateTime、Instant
     *
     * LocalDate、LocalTime、LocalDateTime、Instant为不可变对象，
     * 修改这些对象对象会返回一个副本。增加、减少年数、月数、天数等 以LocalDateTime为例
     */
    private static void modifyLocalJdk8T(){
        // 创建日期：2019-09-10 14:46:56
        LocalDateTime localDateTime = LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 56);
        System.out.println(StrUtil.format("LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 56) 结果为 [{}]", localDateTime));

        //增加一年
        localDateTime = localDateTime.plusYears(1);  //结果： 2020-09-10 14:46:56
        System.out.println(StrUtil.format("localDateTime.plusYears(1) 增加1年结果为  [{}]",localDateTime));

        localDateTime = localDateTime.plus(1, ChronoUnit.YEARS); //结果： 2021-09-10 14:46:56
        System.out.println(StrUtil.format("localDateTime.plus(1, ChronoUnit.YEARS) 增加1年结果为  [{}]",localDateTime));


        //减少一个月
        localDateTime = localDateTime.minusMonths(1);  //结果： 2021-08-10 14:46:56
        System.out.println(StrUtil.format("localDateTime.minusMonths(1) 减少1个月结果为  [{}]",localDateTime));

        localDateTime = localDateTime.minus(1, ChronoUnit.MONTHS); //结果： 2021-07-10 14:46:56
        System.out.println(StrUtil.format("localDateTime.minus(1, ChronoUnit.MONTHS) 减少1个月结果为  [{}]",localDateTime));


//        通过with修改某些值，年月日时分秒都可以通过with方法设置。
        localDateTime = localDateTime.withYear(2020);
        System.out.println(StrUtil.format("localDateTime.withYear(2020) 修改年份结果为 [{}]", localDateTime));

        localDateTime = localDateTime.with(ChronoField.YEAR, 2022);
        System.out.println(StrUtil.format("localDateTime.with(ChronoField.YEAR, 2022) 修改年份结果为 [{}]", localDateTime));

    }

    /**
     * 如果只是为了获取秒数或者毫秒数，使用System.currentTimeMillis()来得更为方便
     */
    private static void testInstant(){
        // 创建Instant对象
        Instant instant = Instant.now();
        System.out.println(StrUtil.format("Instant.now() 结果为  [{}]", instant));

        // 获取秒
        long currentSecond = instant.getEpochSecond();
        System.out.println(StrUtil.format("Instant.getEpochSecond() 结果为  [{}]", currentSecond));

        // 获取毫秒
        long currentMilli = instant.toEpochMilli();
        System.out.println(StrUtil.format("Instant.toEpochMilli() 结果为  [{}]", currentMilli));
    }

    /**
     * LocalDateTime
     */
    private static void testLocalDateTime(){

        LocalDate localDate = LocalDate.now() ;
        LocalTime localTime = LocalTime.now() ;

        //获取当前的日期和时间
        LocalDateTime now = LocalDateTime.now() ;
        System.out.println(StrUtil.format("LocalDateTime.now() 获取当前的日期和时间 [{}],格式化后为[{}]", now,now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.ms"))));

        // 设置日期
        LocalDateTime localDateTime1 = LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 56);
        System.out.println(StrUtil.format("LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 56) 结果为 [{}]", localDateTime1));

        LocalDateTime localDateTime2 = LocalDateTime.of(localDate, localTime);
        System.out.println(StrUtil.format("LocalDateTime.of(localDate, localTime) 结果为 [{}]", localDateTime2));


        LocalDateTime localDateTime3 = localDate.atTime(localTime);
        System.out.println(StrUtil.format("localDate.atTime(localTime) 结果为 [{}]", localDateTime3));

        LocalDateTime localDateTime4 = localTime.atDate(localDate);
        System.out.println(StrUtil.format("localTime.atDate(localDate) 结果为 [{}]", localDateTime4));


        // 获取LocalDate
        LocalDate localDate2 = now.toLocalDate();
        System.out.println(StrUtil.format("now.toLocalDate() 结果为[{}]",localDate2));

        // 获取LocalTime
        LocalTime localTime2 = now.toLocalTime();
        System.out.println(StrUtil.format("now.toLocalTime() 结果为[{}]",localTime2));

    }



    /**
     * LocalTime的各种API
     */
    private static void testLocalTime(){

        // 获取当前时间
        LocalTime localTime = LocalTime.now() ;
        System.out.println(StrUtil.format("LocalTime.now() 获取当前时间 [{}]", localTime));


        // 设置时间
        localTime = LocalTime.of(18,6,45,234) ;
        System.out.println(StrUtil.format("LocalTime.of(18,6,45,234) 设置时间为[{}]", localTime));

        //获取小时
        int hour = localTime.getHour() ;
        int hour1 = localTime.get(ChronoField.HOUR_OF_DAY) ;
        System.out.println(StrUtil.format("localTime.getHour() 的结果为[{}] ==== localTime.get(ChronoField.HOUR_OF_DAY) 的结果为[{}]", hour, hour1));

        //获取分
        int minute = localTime.getMinute() ;
        int minute1 = localTime.get(ChronoField.MINUTE_OF_HOUR) ;
        System.out.println(StrUtil.format("localTime.getMinute() 的结果为[{}] ==== localTime.get(ChronoField.MINUTE_OF_HOUR) 的结果为[{}]", minute, minute1));

        //获取秒
        int second = localTime.getSecond() ;
        int second1 = localTime.get(ChronoField.SECOND_OF_MINUTE) ;
        System.out.println(StrUtil.format("localTime.getSecond() 的结果为[{}] ==== localTime.get(ChronoField.SECOND_OF_MINUTE) 的结果为[{}]", second, second1));
    }





    /**
     * LocalDate的各种API
     */
    private static void testLocalDate(){

        // 获取当前日期
        LocalDate now = LocalDate.now();
        System.out.println(StrUtil.format("LocalDate.now()获取当前日期===> [{}]",now));

        // 设置日期
        LocalDate localDate = LocalDate.of(2019, 9, 10);
        System.out.println(StrUtil.format("LocalDate.of(2019, 9, 10) 设置日期===> [{}]",localDate));

        // 获取年
        int year = localDate.getYear();     //结果：2019
        int year1 = localDate.get(ChronoField.YEAR); //结果：2019
        System.out.println(StrUtil.format("localDate.getYear() 获取年 [{}] ====== localDate.get(ChronoField.YEAR) 获取年[{}]", year, year1));

        // 获取月
        Month month = localDate.getMonth();   // 结果：SEPTEMBER
        int month1 = localDate.get(ChronoField.MONTH_OF_YEAR); //结果：9
        System.out.println(StrUtil.format("localDate.getMonth() 获取月 [{}] ====== localDate.get(ChronoField.MONTH_OF_YEAR)) 获取月[{}]", month, month1));

        // 获取日
        int day = localDate.getDayOfMonth();   //结果：10
        int day1 = localDate.get(ChronoField.DAY_OF_MONTH); // 结果：10
        System.out.println(StrUtil.format("localDate.getDayOfMonth() 获取日 [{}] ====== localDate.get(ChronoField.DAY_OF_MONTH)) 获取日[{}]", day, day1));


        // 获取星期
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();   //结果：TUESDAY
        int dayOfWeek1 = localDate.get(ChronoField.DAY_OF_WEEK); //结果：2
        System.out.println(StrUtil.format("localDate.getDayOfWeek() 获取星期[{}] ====== localDate.get(ChronoField.DAY_OF_WEEK) 获取星期[{}]",dayOfWeek,dayOfWeek1));

    }

}
