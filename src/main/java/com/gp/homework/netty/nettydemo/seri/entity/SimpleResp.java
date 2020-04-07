package com.gp.homework.netty.nettydemo.seri.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class SimpleResp implements Serializable {
    private static Long serialVersionUID = 1L ;
    private int subReqId ;
    private int respCode ;
    private String desc ;


}
