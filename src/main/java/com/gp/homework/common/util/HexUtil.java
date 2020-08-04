package com.gp.homework.common.util;

/**
 * Create by Wayne on 2020/4/26
 */
public class HexUtil {


    public static int myByteArray2Int(byte[] bytes){
        return (bytes[0] & 0x000000FF) << 24 |
               (bytes[1] & 0x000000FF) << 16 |
               (bytes[2] & 0x000000FF) << 8 |
               (bytes[3] & 0x000000FF) ;
    }


    public static byte[] intToByteArray(int i) {
//        byte[] result = new byte[4];
//        // 由高位到低位
//        result[0] = (byte) ((i >> 24) & 0xFF);
//        result[1] = (byte) ((i >> 16) & 0xFF);
//        result[2] = (byte) ((i >> 8) & 0xFF);
//        result[3] = (byte) (i & 0xFF);
        return new byte[]{(byte) ((i >> 24) & 0xFF),(byte) ((i >> 16) & 0xFF),(byte) ((i >> 8) & 0xFF),(byte) (i & 0xFF)};
    }

    @Deprecated
    public static int byteArrayToInt(byte[] bytes) {
        int value = 0;
        // 由高位到低位
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (bytes[i] & 0x000000FF) << shift;// 往高位游
        }
        return value;
    }


    public static void main(String[] args) {


    }
}
