package com.gp.homework.callback.programmer;

/**
 * Create by Wayne on 2020/4/28
 */
public class CallbackTest {
    public static void main(String[] args) {
        ProjectManager prjMgr = new ProjectManager("王响");
        prjMgr.arrange("今晚完成数据库设计...");
        prjMgr.doOtherWork();
    }
}
