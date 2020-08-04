package com.gp.homework.pattern.template.mytest;

/**
 * Create by Wayne on 2020/6/28
 */
public abstract class SuperAbstracClass {


    public String doSomething(InterfaceA a){
        doIam(a) ;
       String str =  toString(a) ;

        return str ;
    }


    public String doIam(InterfaceA a){
        return a.hello("Jiayi");
    }

    public String toString(InterfaceA a){
        return a.toString("11") ;
    }

}
