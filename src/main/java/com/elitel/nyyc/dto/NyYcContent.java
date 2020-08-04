package com.elitel.nyyc.dto;

import java.util.List;

/**
 * Create by Wayne on 2020/6/29
 */
public class NyYcContent<T extends NyBaseEmpty> {

    private List<T> resultList ;
    private byte[] backCmd ;

}
