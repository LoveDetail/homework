package com.gp.homework.controller.transaction;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Create by Wayne on 2020/8/4
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class TransactionUser {

    private Long id ;
    private String email ;
    private String nick_name ;
    private String pass_word ;
    private String reg_time ;
    private String user_name ;


    public TransactionUser(){}
    public TransactionUser(Long id){
        this.id = id ;
    }

}
