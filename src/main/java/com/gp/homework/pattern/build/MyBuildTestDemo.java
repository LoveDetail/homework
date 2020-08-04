package com.gp.homework.pattern.build;

/**
 * Create by Wayne on 2020/8/4
 */
public class MyBuildTestDemo {

    public static void main(String[] args) {
        UserDO userDO = UserDO.builder().id(1L).name("wayne").build() ;
        System.out.println(userDO.toString());


        MyBuildUserDO m = MyBuildUserDO.builder().setE_mail("sea_1123").setId(1L).setName("LoveDetail").build() ;
        System.out.println(m);

    }
}
