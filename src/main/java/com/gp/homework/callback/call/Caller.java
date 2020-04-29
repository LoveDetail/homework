package com.gp.homework.callback.call;

/**
 * Create by Wayne on 2020/4/28
 */
public class Caller {
    private MyCallBack myCallback;

    public void doCall(){
        myCallback.func();
    }

    public void setMyCallback(MyCallBack myCallback) {
        this.myCallback = myCallback;
    }
}
