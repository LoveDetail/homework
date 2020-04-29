package com.gp.homework.callback.programmer;

import java.util.concurrent.TimeUnit;

/**
 * Create by Wayne on 2020/4/28
 */
public class Programmer {

    //指定了通知方式
    Notice notice;

    public void receiveTask(String task,Notice notice){
        this.notice = notice;
        try {
            //程序员开始执行任务
            this.excuteTask(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void excuteTask(String task) throws InterruptedException{
        System.err.println("执行项目经理：安排的任务-->"+task);
        //任务执行中
        TimeUnit.SECONDS.sleep(1);
        //任务完成
        this.finished(task);
    }


    public void finished(String task){
        //获取通知项目经理的方法,并发出通知
        //进行回调通知
        notice.noticeMe("你好，你安排的任务"+task+"已经完成！");
    }
}
