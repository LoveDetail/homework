package com.elitel.nyyc;

import com.elitel.nyyc.dto.NyYcContent;

/**
 * Create by Wayne on 2020/6/29
 */
public interface NYMessagesDecode {

    // 根据报文解析出具体的数据集合
    NyYcContent decode(byte[] bytes) ;

}
