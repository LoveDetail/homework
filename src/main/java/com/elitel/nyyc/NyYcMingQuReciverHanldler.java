package com.elitel.nyyc;

import com.elitel.nyyc.dto.NyYcContent;

/**
 * Create by Wayne on 2020/6/29
 */
public class NyYcMingQuReciverHanldler extends NyycReciveHanlderSupport {

    public NyYcMingQuReciverHanldler(byte[] originalBytes) {
        super(originalBytes);
    }

    public NyYcMingQuReciverHanldler(byte[] originalBytes, byte startByte, byte endByte) {
        super(originalBytes, startByte, endByte);
    }

    @Override
    public NyYcContent decode(byte[] simpleBytes) {
        return null ;
    }
}
