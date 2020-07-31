package com.gp.homework.jdk.compare;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.TreeSet;

/**
 * Create by Wayne on 2020/7/31
 */
public class MyTestComparetorDemo {

    public static void main(String[] args) {
        Set<MyUser> set = new TreeSet<>((o1, o2) -> {
            if(o1 == null && o2 == null ) return 0 ;
            if(o1 == null) return -1 ;
            if(o2 == null) return 0;

            return o1.getAge() - o2.getAge() ;
        });
        for (int i = 0; i< 10; i++) {
            set.add(new MyUser("girl "+i, i));
        }
        set.stream().forEach(myUser -> System.out.println(myUser.getAge()));
    }
}



@Getter
@Setter
class MyUser {

    private String name ;
    private Integer age ;

    public MyUser(String name,int age){
        this.name = name ;
        this.age = age ;
    }
}
