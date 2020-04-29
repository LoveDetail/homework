package com.gp.homework.callback.call;

/**
 * Create by Wayne on 2020/4/28
 */
public class CallBackTest {
    public static void main(String[] args) {

        Caller caller = new Caller();

        //实例化具体回调函数，实现回调方法
        caller.setMyCallback(new MyCallBack() {
            @Override
            public void func() {
                System.out.println("Hello world");
            }
        });

        caller.doCall();
    }
}
