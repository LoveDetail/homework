package com.gp.homework.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Create by Wayne on 2020/6/11
 */

@Getter
@Setter
@ToString
public class TestUsers implements Cloneable{
    private Integer id ;
    private String username ;
    private String password ;
    private String user_sex ;
    private String nick_name ;

    public TestUsers clone(){
        try {
            return (TestUsers)super.clone() ;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null ;
    }

}
