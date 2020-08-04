package com.gp.homework.pattern.template.mytest;

/**
 * Create by Wayne on 2020/6/28
 */
public class SuperAbstractDemo extends SuperAbstracClass{

    public static void main(String[] args) {

        SuperAbstracClass a = new SuperAbstractDemo() ;
        System.out.println(((SuperAbstractDemo) a).doSomething());
    }


    public String doSomething(){


        return super.doSomething(new InterfaceA() {
            @Override
            public <T> String toString(T t) {
                return "Liliwei";
            }
        }) ;
    }

}
