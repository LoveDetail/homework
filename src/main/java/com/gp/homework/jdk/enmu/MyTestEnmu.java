package com.gp.homework.jdk.enmu;

/**
 * Create by Wayne on 2020/5/12
 */
public enum MyTestEnmu {

    Green("0","green"),Red("1","red"),Blue("2","blue") ;

    public String code ;
    public String name;

     MyTestEnmu(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }}
