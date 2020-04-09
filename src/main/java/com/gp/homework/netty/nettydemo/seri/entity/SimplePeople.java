package com.gp.homework.netty.nettydemo.seri.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@EqualsAndHashCode
public class SimplePeople implements Serializable {
    private static Long serialVersionUID = 1L ;

    private int subReqId ;
    private String userName ;
    private String productName ;
    private String photoNumber ;
    private String address ;


    public SimplePeople setSubReqId(int subReqId) {
        this.subReqId = subReqId;
        return this ;
    }

    public SimplePeople setUserName(String userName) {
        this.userName = userName;
        return this ;
    }

    public SimplePeople setProductName(String productName) {
        this.productName = productName;
        return this ;
    }

    public SimplePeople setPhotoNumber(String photoNumber) {
        this.photoNumber = photoNumber;
        return this ;
    }

    public SimplePeople setAddress(String address) {
        this.address = address;
        return this ;
    }
}
