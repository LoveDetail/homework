package com.gp.homework.jdk.compare;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Create by Wayne on 2020/7/31
 */
public class MyTestCompareableDemo {



    public static void main(String[] args) {
        List<SimpleUser> list = new ArrayList<>() ;
        for(int i=0; i<10; i++){
            list.add(new SimpleUser("wayne"+i,i)) ;
        }

        Collections.shuffle(list);
        Collections.sort(list);

        list.forEach((user)->System.out.println(user.getAge()));

    }


}

@Getter
@Setter
class SimpleUser implements Comparable<SimpleUser>{
    private String name ;
    private Integer age ;

    public SimpleUser(String name,int age){
        super() ;
        this.name = name ;
        this.age = age ;
    }

    @Override
    public int compareTo(SimpleUser o) {

        if(o == null) return 1 ;
        if(this.age == o.age) return 0 ;

        return this.age - o.age ;
    }
}