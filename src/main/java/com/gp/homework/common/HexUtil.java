package com.gp.homework.common;

import cn.hutool.core.util.StrUtil;

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
        byte[] bytes = new byte[]{0,0,0x02,0x04} ;

        int result = myByteArray2Int(bytes) ;

        int number = result & 0B0000000000111111 ;
        int type = (result & 0B0000111111000000) >>> 6 ;

        System.out.println(StrUtil.format("{}***{}***{}",result,number,type));

        switch (type) {
            case 0:
                System.out.println(StrUtil.format("{}***空气温度", 0));
                break;
            case 1:
                System.out.println(StrUtil.format("{}***空气湿度", 1));
                break;
            case 2:
                System.out.println(StrUtil.format("{}***CO2", 2));
                break;
            case 3:
                System.out.println(StrUtil.format("{}***风速", 3));
                break;
            case 4:
                System.out.println(StrUtil.format("{}***风向", 4));
                break;
            case 5:
                System.out.println(StrUtil.format("{}***雨雪信号", 5));
                break;
            case 6:
                System.out.println(StrUtil.format("{}***雨量", 6));
                break;
            case 7:
                System.out.println(StrUtil.format("{}***水暖水温", 7));
                break;
            case 8:
                System.out.println(StrUtil.format("{}***土壤温度", 8));
                break;
            case 9:
                System.out.println(StrUtil.format("{}***土壤湿度", 9));
                break;
            case 10:
                System.out.println(StrUtil.format("{}***压差", 10));
                break;
            case 11:
                System.out.println(StrUtil.format("{}***Ph", 11));
                break;
            case 12:
                System.out.println(StrUtil.format("{}***Ec", 12));
                break;
            case 13:
                System.out.println(StrUtil.format("{}***光亮度", 13));
                break;
            case 14:
                System.out.println(StrUtil.format("{}***空照辐射", 14));
                break;
            case 15:
                System.out.println(StrUtil.format("{}***流量(升)", 15));
                break;


        }
    }
}
