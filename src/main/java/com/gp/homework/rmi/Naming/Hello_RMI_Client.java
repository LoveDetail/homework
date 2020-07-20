package com.gp.homework.rmi.Naming;

import java.rmi.Naming;

/**
 * Create by Wayne on 2020/7/20
 */
public class Hello_RMI_Client {

    public static void main(String[] args) {
        try {
            IHello hello = (IHello) Naming.lookup("rmi://localhost:1099/hello");
            System.out.println(hello.sayHello("LoveDetail"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
