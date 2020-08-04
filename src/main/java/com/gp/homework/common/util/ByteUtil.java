package com.gp.homework.common.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;

public class ByteUtil {

    /**
     * 一个short是两个字节，把一个short类型的书转化为byte[]长度为2的数字返回，beReverse表示是否翻转高低位，如果为true，则低位在前返回
     * C
     * @param data 需要返回的short类型数
     * @param beReverse 是否需要翻转，false为不需要翻转，如果为true，则低位在前返回
     */
    public static byte[] shortToBytes(short data, boolean beReverse) {
        return ByteBuffer.allocate(Short.BYTES).order(beReverse ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN).putShort(data).array();
    }

    /**
     * 一个short是两个字节，把一个short类型的书转化为byte[]长度为2的数组返回，高位在前
     * C
     * @param data 需要返回的short类型数
     */
    public static byte[] shortToBytes(short data) {
        return shortToBytes(data,false) ;
    }

    /**
     * 一个char是两个字节，把一个char类型的书转化为byte[]长度为2的数组返回，高位在前
     * C
     * @param data 需要返回的short类型数
     */
    public static byte[] charToBytes(char data) {
        return ByteBuffer.allocate(Character.BYTES).order(ByteOrder.BIG_ENDIAN ).putChar(data).array();
    }

    /**
     * 一个int是4个字节，把一个int类型的书转化为byte[]长度为2的数组返回，beReverse表示是否翻转高地位，如果为true，则低位在前返回
     * C
     *
     * @param intData 需要返回的int类型数
     * @param beReverse 是否需要翻转，false为不需要翻转，如果为true，则低位在前返回
     */
    public static byte[] intToBytes(int intData, boolean beReverse) {
        return ByteBuffer.allocate(Integer.BYTES).order(beReverse ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN).putInt(intData).array();
    }

    /**
     * 一个int是4个字节，把一个int类型的书转化为byte[]长度为4的数组返回,高位在前
     * C
     * @param data 需要返回的int类型数
     */
    public static byte[] intToBytes(int data) {
        return intToBytes(data,false) ;
    }


    /**
     * 一个long是8个字节，把一个long类型的书转化为byte[]长度为8的数组返回
     * C
     *
     * Long.BYTES = 8
     *
     * @param data 需要返回的int类型数
     */
    public static byte[] longToBytes(long data) {
        return ByteBuffer.allocate(Long.BYTES).order(ByteOrder.BIG_ENDIAN).putLong(data).array();
    }

    /**
     * 将字节数组转化为short数字：
     * 1、如果字节数组为空或长度为0，则抛出异常
     * 2、将 index 下标的一个字节转化为short
     *
     * CC
     * @param index byte数组的起始下标、下标、下标   eg：如果是数字的第一位开始，则 index = 0 ;
     * @param bytes 需要转换的byte数组，如果数组长度为0或者为null，抛出异常
     * @param beReverse 是否需要先翻转数组，true翻转，false不翻转
     */
    public static short bytesToShort(byte[] bytes, int index, boolean beReverse) {
        return bytesToShort(bytes, index, 1, beReverse);
    }


    /**
     * 将字节数组转化为short数字：
     * 1、如果字节数组为空或长度为0，则抛出异常
     * 2、如果需要翻转,则截取startIndex开始的length个长度之后，进行翻转，不是全部bytes翻转
     * 3、如果数组长度超过两位，那么翻转后把翻转所得数组的低位进行转换
     * C
     *
     * @param startIndex byte数组的起始下标、下标、下标   eg：如果是数字的第一位开始，则startIndex = 0 ;
     * @param length 以startIndex起始下标截取的长度
     * @param bytes 需要转换的byte数组，如果数组长度为0或者为null，抛出异常
     * @param beReverse 是否需要先翻转数组，true翻转，false不翻转
     */
    public static short bytesToShort(byte[] bytes,int startIndex,int length, boolean beReverse) {
        if (ObjectUtil.isEmpty(bytes) || length <= 0 || length > Short.BYTES)
            throw new IllegalArgumentException("数组参数byte[] bytes不能为空,且length参数只能为1或者2 !!!");

        if (bytes.length < startIndex + length)
            throw new IllegalArgumentException(StrUtil.format("参数startIndex异常，bytes数组长度为{},不足以从startIndex为{}位置截取{}个字节长度进行short类型转换!!!", bytes.length, startIndex, length));

        byte[] sourceBytes = new byte[length];
        System.arraycopy(bytes, startIndex, sourceBytes, 0, length);

        sourceBytes = beReverse ? reverse(sourceBytes) : sourceBytes;
        sourceBytes = length == 1 ? new byte[]{0x00, sourceBytes[0]} : new byte[]{sourceBytes[0], sourceBytes[1]};

        return  (short)((sourceBytes[0] & 0xFF) << Byte.SIZE | sourceBytes[1]&0xFF);
    }


    /**
     * 将字节数组转化为char字符：
     * 1、如果字节数组为空或以startIndex起始的length个长度不为2字节，则抛出异常！
     * 2、根据beReverse进行数组高低位转换，为false时不转换
     * 3、如果不足2位的话高位补0,且传入的startIndex必须为0，否则抛出异常
     * 4、如果字节数组超过2个字节，则截取数组下标以startIndex开始的length(1或2)个字节进行转换，截取长度不足报异常
     * C
     *
     * @param bytes 需要转换的byte数组，如果数组长度为0或者为null，抛出异常
     * @param startIndex byte数组的起始下标、下标、下标   eg：如果是数字的第一位开始，则startIndex = 0 ;
     * @param length 以startIndex起始下标截取的长度
     * @param beReverse 是否需要先翻转数组(只)，true翻转，false不翻转
     */
    public static char bytesToChar(byte[] bytes,int startIndex,int length,boolean beReverse) {

        if (ObjectUtil.isEmpty(bytes) || length <= 0 || length > Character.BYTES)
            throw new IllegalArgumentException("数组参数byte[] bytes不能为空,且length参数只能为1或者2 !!!");

        if (bytes.length < startIndex + length)
            throw new IllegalArgumentException(StrUtil.format("参数startIndex异常，bytes数组长度为{},不足以从startIndex为{}位置截取{}个字节长度进行char类型转换!!!", bytes.length, startIndex, length));

        byte[] sourceBytes = new byte[length];
        System.arraycopy(bytes, startIndex, sourceBytes, 0, length);

        return (char) bytesToInt(beReverse ? reverse(sourceBytes) : sourceBytes, false);
    }

    /**
     * 将字节数组转化为char字符：
     * 1、如果字节数组为空抛出异常！
     * 2、根据beReverse进行数组高低位转换，为false时不转换，只翻转有效位，不会全部翻转
     * 3、如果不足2字节的话高位补0进行转换
     * 4、如果字节数组超过2个字节，则截取数组的最低位2个字节进行转换
     * C
     *
     * @param bytes 待转换的数组
     * @param beReverse 是否翻转 true翻转，false不翻转
     */
    public static char bytesToChar(byte[] bytes, boolean beReverse) {
        if (ObjectUtil.isEmpty(bytes))
            throw new IllegalArgumentException("数组参数byte[] bytes不能为空");

        int size = bytes.length;
        return bytesToChar(bytes, size > Character.BYTES ? bytes.length - Character.BYTES : 0, size > Character.BYTES ? Character.BYTES : size, beReverse);
    }


    /**
     * 将字节数组转化为int类型数：
     * 1、如果字节数组为空或以startIndex起始的长度不为4字节，则抛出异常！，
     * 2、根据beReverse进行数组高低位转换，为false时不转换,只转换有效部分
     * 3、如果不足4位的话高位补0,且传入的startIndex必须为0，否则抛出异常
     * 4、如果字节数组超过4字节，则截取数组下标以startIndex开始的4个字节进行转换，长度不足报异常
     * C
     *
     * @param bytes 输入的字节数组
     * @param index 将数组的第index位单独转化为int，高位补零，如从数组第一个元素开始，则index为0
     * @param beReverse 是否翻转，false 不翻转，true 翻转
     */
    public static int bytesToInt(byte[] bytes, int index, boolean beReverse) {
        return bytesToInt(bytes, index, 1, beReverse);
    }


    /**
     * 将字节数组转化为int类型数：
     * 1、如果字节数组为空或数组长度为0则抛出异常！
     * 2、根据beReverse进行数组高低位转换，为false时不转换，只转换有效部分
     * 2、如果字节数组不足4位的话高位补0
     * 3、如果字节数组超过4字节，则根据数组的低四位进行转换
     * C
     *
     * @param bytes 输入的字节数组
     * @param beReverse 是否翻转，false 不翻转，true 翻转
     */
    public static int bytesToInt(byte[] bytes,boolean beReverse) {
        if (ObjectUtil.isEmpty(bytes))
            throw new IllegalArgumentException("参数bytes[]为空，不足以进行int类型转换!!!");

        int byteSize = bytes.length;

        return bytesToInt(bytes, byteSize > Integer.BYTES ? byteSize - Integer.BYTES : 0, byteSize > Integer.BYTES ? Integer.BYTES : byteSize, beReverse);
    }


    /**
     * 将字节数组转化为int类型数：
     * 1、如果字节数组为空或以startIndex起始的length个长度不足4字节，则抛出异常！
     * 2、根据beReverse进行数组高低位转换，为false时不转换，只转换有效部分（以startIndex开始的length个长度）
     * 3、length取值为0<length<5的数，如果小于4的话，截取的数组高位补0进行int型转换
     * C
     *
     * @param bytes 待转换的原始数组
     * @param startIndex 需要转换的起始数组坐标，eg： 如果取数组的第一位，startIndex = 0
     * @param length 以startIndex开始截取的长度，长度最大为4，不允许为0，否则抛出异常
     * @param beReverse 是否高低位翻转原始数组，false不翻转，true翻转
     */

    public static int bytesToInt(byte[] bytes, int startIndex, int length, boolean beReverse) {
        if (ObjectUtil.isEmpty(bytes))
            throw new IllegalArgumentException("参数bytes[]为空，不足以进行int类型转换!!!");

        if (length < 0 || length > Integer.BYTES)
            throw new IllegalArgumentException(StrUtil.format("参数length的取值范围只能为 0 < length <=4,当前length值为{}", length));

        if ((startIndex + length) > bytes.length)
            throw new IllegalArgumentException(StrUtil.format("参数start和length异常，bytes数组长度为{},不足以从start为{}位置截取{}个字节长度进行int类型转换!!!", bytes.length, startIndex, length));

        byte[] sourceBytes = new byte[length], targetBytes = new byte[]{0, 0, 0, 0};
        System.arraycopy(bytes, startIndex, sourceBytes, 0, length);

        System.arraycopy(beReverse ? reverse(sourceBytes) : sourceBytes,0,targetBytes,Integer.BYTES-length,length);

        return (targetBytes[0] & 0xFF) << 0x18 |
               (targetBytes[1] & 0xFF) << 0x10 |
               (targetBytes[2] & 0xFF) << 0x08 |
               (targetBytes[3] & 0xFF);
    }


    /**
     * 将字节数组转化为long类型数：
     * 1、如果字节数组为空或以startIndex起始的长度不为8字节，则抛出异常！
     * 2、根据beReverse进行数组高低位转换，为false时不转换,只转换有效部分（以startIndex开始的length个长度）
     * 3、length取值为0<length<9的数，如果小于8的话，截取的数组高位补0进行long型转换
     * C
     *
     * @param bytes 待转换的原始数组
     * @param startIndex 需要转换的起始数组坐标，eg： 如果取数组的第一位，startIndex = 0
     * @param length 以startIndex开始截取的长度，长度最大为8，不允许为0，否则抛出异常
     * @param beReverse 是否高低位翻转原始数组，false不翻转，true翻转
     */
    public static long bytesToLong(byte[] bytes,int startIndex,int length,boolean beReverse) {
        if (ObjectUtil.isEmpty(bytes))
            throw new IllegalArgumentException("参数bytes[]为空，不足以进行long类型转换!!!");

        if (length <= 0 || length > Long.BYTES)
            throw new IllegalArgumentException(StrUtil.format("参数length的取值范围只能为 0 < length <=8,当前length值为{}", length));

        if ((startIndex + length) > bytes.length)
            throw new IllegalArgumentException(StrUtil.format("参数start和length异常，bytes数组长度为{},不足以从start为{}位置截取{}个字节长度进行long类型转换!!!", bytes.length, startIndex, length));

        byte[] targetBytes = new byte[]{0, 0, 0, 0, 0, 0, 0, 0};
        System.arraycopy(bytes, startIndex, targetBytes, Long.BYTES - length, length);

        targetBytes = beReverse ? reverse(targetBytes) : targetBytes;

        return (targetBytes[0] & 0XFFL) << 0x38 |
               (targetBytes[1] & 0XFFL) << 0x30 |
               (targetBytes[2] & 0XFFL) << 0x28 |
               (targetBytes[3] & 0XFFL) << 0x20 |
               (targetBytes[4] & 0XFFL) << 0x18 |
               (targetBytes[5] & 0XFFL) << 0x10 |
               (targetBytes[6] & 0XFFL) << 0x08 |
               (targetBytes[7] & 0XFFL);
    }

    /**
     * 将字节数组转化为long类型数：
     * 1、如果字节数组为空或数组长度为0则抛出异常！
     * 2、根据beReverse进行数组高低位转换，为false时不转换
     * 3、如果字节数组不足8位的话高位补0
     * 4、如果字节数组超过8字节，则根据数组的低8位进行转换
     * C
     *
     * @param bytes 输入的字节数组
     * @param beReverse 是否翻转，false 不翻转，true 翻转
     */
    public static long bytesToLong(byte[] bytes,boolean beReverse) {
        if (ObjectUtil.isEmpty(bytes))
            throw new IllegalArgumentException("参数bytes[]为空，不足以进行long类型转换!!!");

        int byteSize = bytes.length;
        return bytesToLong(bytes, byteSize > Long.BYTES ? byteSize - Long.BYTES : 0, byteSize > Long.BYTES ? Long.BYTES : byteSize, beReverse);
    }


    /**
     * 从bytes数组的startIndex位开始，截取length个长度进行int_16的转换
     * 1、如果字节数组为空或以startIndex起始的长度不为4字节，则抛出异常！
     * 2、根据beReverse进行数组高低位转换，为false时不转换
     * 3、length取值为0<length<5的数，如果小于4的话，截取的数组高位补0进行int_16型转换
     * 4、最终运算结果取int型的低两位字节进行int_16的转换
     * C
     *
     * @param bytes 待转换的原始数组
     * @param startIndex 需要转换的起始数组坐标，eg： 如果取数组的第一位，startIndex = 0
     * @param length 以startIndex开始截取的长度，长度最大为4，不允许为0，否则抛出异常
     * @param beReverse 是否高低位翻转原始数组，false不翻转，true翻转(只翻转有效位，以startIndex开始截取length的部分数组)
     */
    public static int bytesTo16Int(byte[] bytes, int startIndex,int length, boolean beReverse) {
        return bytesToInt(bytes, startIndex, length, beReverse) & 0x0000FFFF;
    }


    /**
     * 从bytes数组的start位开始，截取两位进行int_16的转换
     * 1、如果字节数组为空或以start起始的长度不足2字节，则抛出异常！
     * 2、根据beReverse进行数组高低位转换，为false时不转换
     * 3、length取值为0<length<5的数，如果小于4的话，截取的数组高位补0进行int_16型转换
     * 4、最终运算结果取int型的低两位字节进行int_16的转换
     * C
     *
     * byte数组转int16类型的对象
     * @return int
     */
    public static int byteTo16int(byte[] bytes, int start, boolean beReverse) {
        return bytesTo16Int(bytes, start, 2, beReverse);
    }

    /**
     * hex字符串转int16类型的对象
     * @param hexStr int
     * @return Integer
     */
    //TODO
    public static Integer hexStrToUInt16(String hexStr) {
        if (StrUtil.isNullOrUndefined(hexStr) || StrUtil.isEmpty(hexStr))
            return null;

        try {
            String hex = hexStr.toUpperCase().substring(0, hexStr.length() - 1);
            int index = hexStr.toUpperCase().indexOf("H");
            if (index > 0) {
                hex = hexStr.toUpperCase().substring(0, index);
            }

            return Integer.parseInt(hex, 16);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     *
     * 2020-05-22 作废，Liliwei，建议直接使用 bytes2Int 方法
     * byte数组转int32类型的对象
     *
     */
    @Deprecated
    public static int byteTo32int(byte[] bytes, int start, boolean beReverse) {
        return bytesToInt(bytes, start, beReverse);
    }


    /**
     * byte数组反转
     * C
     *
     * @param arr 待翻转数组
     */
    public static byte[] reverse(byte[] arr) {
        byte[] targetBytes = new byte[arr.length];
        for (int i = 0, size = arr.length; i < size; i++) {
            targetBytes[size - 1 - i] = arr[i];
        }
        return targetBytes;
    }

    /**
     * 字节数组转对应的HEX字符串
     * C
     *
     * @param bytes  待转换的数组
     */
    public static String bytesToHexString(byte[] bytes) {

        if (ObjectUtil.isEmpty(bytes))
            throw new IllegalArgumentException("待转换数组bytes不能为空!!! ");

        StringBuilder sb = new StringBuilder(bytes.length);
        for (byte b : bytes) {
            String sTemp = Integer.toHexString(0xFF & b);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 字节数组转对应的HEX字符串
     * CC
     *
     * @param bytes  待转换的数组
     * @param startIndex 数组的起始下标编号
     * @param length 数组的截取长度
     * @param cc 每个字节之间的间隔字符,不允许为null，否则抛出异常
     */
    public static String bytesToHexString(byte[] bytes, int startIndex, int length, String cc) {
        if (ObjectUtil.isEmpty(bytes))
            throw new IllegalArgumentException("待转换数组bytes不能为空!!! ");
        if (startIndex + length > bytes.length)
            throw new IllegalArgumentException(StrUtil.format("参数startIndex和length异常，bytes数组长度为{},不足以从startIndex为{}位置截取{}个字节长度进行HexString转换!!!", bytes.length, startIndex, length));
        if (cc == null)
            throw new NullPointerException("参数cc为NULL。。。");

        StringBuilder sb = new StringBuilder(length);
        String sTemp;
        for (int i = startIndex; i < bytes.length && i < startIndex + length; i++) {
            sTemp = Integer.toHexString(0xFF & bytes[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
            sb.append(cc); //自动加上分隔
        }
        return sb.toString();
    }

    /**
     * 16进制字符串转对应的字节数组,hexString 由16进制的数字组成，按照16进制的转换规则，生成转换后的16进制byte数组
     * C
     *
     * @param hexString 十六进制字符串,不允许为null或空字符串，参数里的H和空格都会被自动替换，方法内会进行16进制的格式判定，如果格式有误，则会抛出异常 ;
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (StrUtil.isEmpty(hexString))
            throw new IllegalArgumentException("待转换字符串hexString不能为空!!! ");

        String tempStr = hexString.
                replace(" ","").
                replace("H", "").
                replace("h", "").
                replace("\\s+", "").
                replace("0x", "").
                replace("0X", "");


        if (!isHexString(tempStr)) {
            throw new IllegalArgumentException(StrUtil.format("参数 hexString = {}不是标准的16进制字符串，请确认参数格式正确!!!", tempStr));
        }

        if ((tempStr.length() & 1) == 1) //位数不是奇数，则在首位补“0”
            throw new IllegalArgumentException(StrUtil.format("参数 hexString = {}位数为奇数，请确认参数位数正确!!!", tempStr));

        return Convert.hexToBytes(tempStr);
    }

    /**
     * HEX字符串转对应的字节集合
     * C
     *
     * @param hexString 十六进制字符串,不允许为null或空字符串，参数里的H和空格都会被自动替换，方法内会进行16进制的格式判定，如果格式有误，则会抛出异常 ;
     * @return 返回Byte的List数组
     */
    //TODO 方法太Low，后续改善
    public static List<Byte> hexStrToByteArray(String hexString) {

        List<Byte> targetList = CollUtil.newArrayList();
        for (Byte b : hexStringToBytes(hexString)) {
            targetList.add(b);
        }
        return targetList;
    }

    /**
     * 不说了，太Low
     * @param list
     * @return
     */
    public static byte[] byteOfListToArray(List<Byte> list) {

        if (CollUtil.isEmpty(list))
            throw new IllegalArgumentException("待转换list不能为空!!! ");

        int size = list.size() ;

        byte[] bytes = new byte[size] ;
        for(int i=0; i<size; i++){
            bytes[0] = list.get(0) ;
        }
        return bytes;
    }

    /**
     * 16进制字符串转对应的字节，如果hexStr长度超过两位，则取前两位进行转换，后续部分忽略 eg：0x450x27 ==> byte[]{0x69}
     * C
     *
     * @param hexStr 十六进制字符串
     * @return 返回Byte字节
     */
    public static byte hexStrToByte(String hexStr) {
        return hexStringToBytes(hexStr)[0];
    }

    /**
     *
     *
     * 10进制串转为Byte数组码
     * @param bcdStr 10进制串
     * @return byte数组
     */
    @Deprecated
    public static byte[] bcdstrToBytes(String bcdStr) {
        return bcdStr2Bytes(bcdStr);
    }

    /**
     * BCD串转为16进制的字节数组 如 65003461 ==> byte[]{0x65,0x00,0x34,0x61}    457 ==> byte[]{0x04,0x57}
     * C
     *
     * @param bcdStr 待转换的BCD字符串，字符串只能由0-9的数字组成，如果位数为奇数，则在首位补零
     * @return byte数组
     */
    public static byte[] bcdStr2Bytes(String bcdStr) {

        if (StrUtil.isEmpty(bcdStr))
            throw new IllegalArgumentException("待转换字符串 bcdStr 不能为空!!! ");

        if (!isBCDString(bcdStr)) {
            throw new IllegalArgumentException(StrUtil.format("参数 bcdStr = {}不是标准的BCD格式字符串，请确认参数格式正确!!!", bcdStr));
        }

        int len = bcdStr.length();
        if ((len & 1) != 0) {
            bcdStr = "0" + bcdStr;
            len = bcdStr.length();
        }


        byte[] result = new byte[len >= 2 ? len >> 1 : len];
        byte[] tempBytes = bcdStr.getBytes();

        for (int p = 0; p < tempBytes.length / 2; p++) {
            byte b = 0;
            byte c = tempBytes[2 * p];
            if ((c >= '0') && (c <= '9')) {
                b = (byte) ((c - '0') << 4);
            }

            c = tempBytes[2 * p + 1];
            if ((c >= '0') && (c <= '9')) {
                b = (byte) (b + (c - '0'));
            }

            result[p] = b;
        }
        return result;
    }

    /**
     * BCD码字节数组转为10进制串(阿拉伯数据)  new byte[]{0x65,0x00,0x34,0x61} ==> 65003461
     * C
     *
     * @param bytes BCD码字节数组
     * @return 10进制串
     */
    public static String bytesToBcdStr(byte... bytes) {

        StringBuilder temp = new StringBuilder(bytes.length << 1);

        for (byte aByte : bytes) {
            temp.append((byte) ((aByte & 0xf0) >>> 4));
            temp.append((byte) (aByte & 0x0f));
        }
        String str = temp.toString();
        return str.startsWith("0") ? str.substring(1) : str;
    }

    /**
     * 字节数组转换成BCD字符串
     * CC
     *
     * eg: byte[]{0x65,0x00,0x34,0x61} startIndex:0 ,length:3 ,beReverse:false ==> 650034
     * eg: byte[]{0x65,0x00,0x34,0x61} startIndex:0 ,length:3 ,beReverse:true ==> 340065
     *
     * @param bytes 字节数组
     * @param startIndex 数组开始的位置索引
     * @param length 转换字节长度
     * @param beReverse 是否反转，只翻转startIndex截取的len部分
     * @return BCD字符串
     */
    public static String bytesToBcdStr(byte[] bytes, int startIndex, int length, boolean beReverse) {

        if (ObjectUtil.isEmpty(bytes))
            throw new IllegalArgumentException("待转换数组bytes不能为空!!! ");

        if (startIndex + length > bytes.length)
            throw new IllegalArgumentException(StrUtil.format("参数startIndex和length异常，bytes数组长度为{},不足以从startIndex为{}位置截取{}个字节长度进行BCD字符串转换!!!", bytes.length, startIndex, length));

        byte[] sourceBytes = new byte[length];
        System.arraycopy(bytes, startIndex, sourceBytes, 0, length);

        sourceBytes = beReverse ? reverse(sourceBytes) : sourceBytes;

        StringBuilder strBuff = new StringBuilder();

        for (int i = 0, size = sourceBytes.length; i < size; i++) {
            strBuff.append((byte) ((sourceBytes[i] & 0xF0) >>> 4));
            strBuff.append((byte) (sourceBytes[i] & 0x0F));
        }
        return strBuff.toString();
    }

    /**
     * 字节数组转换成ASCII字符串 new byte[]{0x30, 0x31, 0x32, 0x33,0x52,83} ==> 0123RS
     * C
     *
     * @param bytes 字节数组 如果长度不足以截取length个长度，则抛出异常 !!!
     * @param startIndex 开始位置索引
     * @param length 转换字节长度
     * @return ASCII字符串
     */
    public static String bytesToAsciiStr(byte[] bytes, int startIndex, int length) {
        return bytesToAsciiStr(bytes, startIndex, length, false);
    }


    /**
     * 字节数组转换成ASCII字符串
     * eg : new byte[]{0x30, 0x31, 0x32, 0x33,0x52,83} , startIndex:0, length:6, beReverse:false==> 0123RS
     * eg : new byte[]{0x30, 0x31, 0x32, 0x33,0x52,83} , startIndex:0, length:6, beReverse:true==> SR3210
     * C
     *
     *
     * @param bytes 字节数组 如果长度不足以截取length个长度，则抛出异常 !!!
     * @param startIndex 开始位置索引
     * @param length 转换字节长度
     * @param beReverse 是否翻转(只翻转startIndex到length长度的部分) true 翻转，false 不翻转
     */
    public static String bytesToAsciiStr(byte[] bytes, int startIndex, int length,boolean beReverse) {

        if (ObjectUtil.isEmpty(bytes))
            throw new IllegalArgumentException("待转换数组bytes不能为空!!! ");

        if (startIndex + length > bytes.length)
            throw new IllegalArgumentException(StrUtil.format("参数startIndex和length异常，bytes数组长度为{},不足以从startIndex为{}位置截取{}个字节长度进行ASCII字符串转换!!!", bytes.length, startIndex, length));

        byte[] sourceBytes = new byte[length];
        System.arraycopy(bytes, startIndex, sourceBytes, 0, length);

        return String.valueOf(StandardCharsets.US_ASCII.decode((ByteBuffer) ByteBuffer.allocate(length).
                        put(beReverse ? reverse(sourceBytes) : sourceBytes, 0, length).
                        flip()).
                array());

    }


    /**
     * ascii字符串转数组
     * C
     *
     * @param data ascii字符串
     * @return 字节数组
     */
    public static byte[] strToAsciiBytes(String data) {
        if (StrUtil.isEmpty(data))
            throw new IllegalArgumentException("参数 data 为空 !!!");

        return StandardCharsets.US_ASCII.encode(data).array();
    }

    /**
     *2020.5.26 @Deprecated by Liliwei ，建议使用 hexStrToByte(String hexStr) 方法
     *C
     *
     * HEX字符串转对应的字节(只取前两位)
     * @param hexStr 16进制字符串
     * @return 前两位byte
     */
    @Deprecated
    public static byte StrToByte(String hexStr) {
        return hexStringToBytes(hexStr)[0] ;
    }

    /**
     * 字符转对应的字节 取低位字节 'Z' ==> byte:0x5A
     * C
     *
     * 这方法太NB了，人才啊～～sice 2020.5.25 Liliwei  (byte)"0123456789".indexOf(char c)
     *
     * @param c char字符
     * @return 返回Char字符对应的byte
     */
    public static byte charToByte(char c) {
        return charToBytes(c)[1];
    }


    /**
     * byte数组转换成二进制字符串
     * CC
     *
     * @param bytes 字节数组
     * @param startIndex 开始位置索引
     * @param length 转换字节长度
     * @param resultReverse 是否对结果字符串反转 ,true 翻转，false 不翻转
     * @return 二进制字符串
     */
    public static String bytesToBinaryStr (byte[] bytes, int startIndex, int length, boolean resultReverse) {

        if (ObjectUtil.isEmpty(bytes))
            throw new IllegalArgumentException("待转换数组bytes不能为空!!! ");

        if (startIndex + length > bytes.length)
            throw new IllegalArgumentException(StrUtil.format("参数startIndex和length异常，bytes数组长度为{},不足以从startIndex为{}位置截取{}个字节长度进行二进制字符串转换!!!", bytes.length, startIndex, length));

        byte[] sourceBytes = new byte[length];
        System.arraycopy(bytes, startIndex, sourceBytes, 0, length);


        return resultReverse ?
                reverseString(hexStringToByteStr(bytesToHexString(sourceBytes, 0, length, ""))) :
                hexStringToByteStr(bytesToHexString(sourceBytes, 0, length, ""));
    }



    /**
     *
     * 2020.05.25 与hexStrToByte 方法冲突，作废 by Liliwei
     *
     * 16进制字符串转byte
     * @param hexStr 待转换的16进制字符串
     */
    @Deprecated
    public static byte hexStr2Byte(String hexStr) {
        return hexStringToBytes(hexStr)[0];
    }

    /**
     * 16进制byte转String字符串
     * C
     *
     * @param b 待转换的16进制byte
     */
    public static String byteToHexStr(byte b) {
        return String.format("%02x", b & 0xff).toUpperCase();
    }

    /**
     * 16进制字符串转2进制字符串
     * C
     *
     * @param hex 16进制字符串
     */
    public static String hexStringToByteStr(String hex) {
        if (StrUtil.isEmpty(hex))
            throw new IllegalArgumentException("参数 hex 为空 !!!");

        String tempStr = hex.
                replace("H", "").
                replace("h", "").
                replace("\\s+", "").
                replace("0x", "").
                replace("0X", "");

        if (!isHexString(hex))
            throw new IllegalArgumentException(StrUtil.format("参数 hex = {}不是标准的16进制字符串，请确认参数格式正确!!!", tempStr));

        return Integer.toBinaryString(Integer.parseInt(hex, 16));
    }

    /**
     * 2进制字符串转10进制int
     * C
     *
     * @param binaryStr 2进制字符串 自动替换   0b/0B/空格
     */
    public static int binaryStrToInt(String binaryStr) {
        if (StrUtil.isEmpty(binaryStr))
            throw new IllegalArgumentException("参数 binaryStr 为空 !!!");

        String tempStr = binaryStr.
                replace("\\s+", "").
                replace("0B", "").
                replace("0b", "");

        if (!isBinaryString(tempStr))
            throw new IllegalArgumentException(StrUtil.format("参数 hex = {}不是标准的16进制字符串，请确认参数格式正确!!!", tempStr));

        return Integer.valueOf(tempStr, 2);
    }

    /**
     * byte数组转ascii
     *
     */
    //TODO
    public static String byteToAsciiStr(byte[] buff) {
        String hexStr = bytesToHexString(buff);
        if (StrUtil.isEmpty(hexStr)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hexStr.length(); i++) {
            int result = Integer.parseInt(hexStr.substring(i, i + 1), 16);
            char c = (char) result;
            sb.append(c);
        }
        return sb.toString();
    }


    /// <summary>
    /// 字节数组前面添加[UDPDATA:电话号码:]前缀
    /// </summary>
    /// <param name="str"></param>
    /// <param name="bs"></param>
    /// <returns></returns>
    public static byte[] UDPDATAJoinBytes(String str, byte[] bs) {
        return StringJoinBytesToBytes(StrUtil.format("UDPDATA:{}:", str), bs);
    }

    /// <summary>
    /// 字符串和字节数组拼接
    /// </summary>
    /// <param name="str"></param>
    /// <param name="bs"></param>
    /// <returns></returns>
    public static byte[] StringJoinBytesToBytes(String str, byte[] bs) {
        byte[] bsPre = strToAsciiBytes(str);

        int bsPreLength = bsPre.length, bsLengh = bs.length;

//        ByteArrayOutputStream ms = new ByteArrayOutputStream();
////        ms.write(bsPre, 0, bsPre.length);
////        ms.write(bs, 0, bs.length);

        byte[] targetBytes = new byte[bsPreLength + bsLengh];
        System.arraycopy(bsPre, 0, targetBytes, 0, bsPreLength);
        System.arraycopy(bs, 0, targetBytes, bsPreLength, bsLengh);

        return targetBytes;
    }

    /**
     * @Deprecated By Liliwei since 2020.5.26 建议使用 strToAsciiBytes（str）
     *
     * ascii字符串转数组
     * @param str ascii字符串
     * @return 字节数组
     */
    @Deprecated
    public static byte[] GetAssIIByteArrayFromString(String str) {

//        byte[] rbyte = new byte[str.length()];
//        for (int i = 0; i < str.length(); i++) {
//            rbyte[i] = charToByte(str.charAt(i));
//        }
        return strToAsciiBytes(str);
    }

    private static boolean isHexString(String hexString) {
        return Pattern.matches("[0-9|a-f|A-F]+", hexString);
    }

    private static boolean isBCDString(String bcdString) {
        return Pattern.matches("[0-9]+", bcdString);
    }

    private static boolean isBinaryString(String binaryString) {
        return Pattern.matches("[0-1]+", binaryString);
    }

    private static String reverseString(String str){

        char[] cc = str.toCharArray() ;
        String result = "" ;
        for(int i=cc.length-1; i != -1 ; i--){
         result += cc[i] ;
        }

        return result ;
    }

}
