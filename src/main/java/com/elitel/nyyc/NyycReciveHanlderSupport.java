package com.elitel.nyyc;

import com.elitel.nyyc.dto.NyYcContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Wayne on 2020/6/29
 */
public abstract class NyycReciveHanlderSupport implements NYMessagesDecode {

    private List<byte[]> byteCacheList ;  //连包情况下，用于进行缓存报文
    private byte[] originalBytes ; // 远传过来的报文数组
    private byte startByte = 0x68;
    private byte endByte = 0x16;
    private boolean isMulti = false ;  //是否多包，默认为false
    private String processStatus = "ok" ; //此处应为枚举，表示当前报文的处理状态，主要用来标记是否多包，如多包未接收完毕，则取值为“go on”


    public NyycReciveHanlderSupport(byte[] originalBytes){
        this.originalBytes = originalBytes ;
    }

    public NyycReciveHanlderSupport(byte[] originalBytes,byte startByte,byte endByte){
        this.originalBytes = originalBytes ;
        this.startByte = startByte ;
        this.endByte = endByte ;
    }


    //DIR=0，表示由中心站发出的下行报文
    //DIR=1，表示由终端发出的上行报文  [控制域的D7进行判定  1 true ,0 false ]
    public boolean checkDir(){

        //TODO 根据符号位的0和1进行判定 [此处伪代码]
        return true ;
    }


    // 报文长度校验,默认长度小于2则认为无效
    public boolean checkMinLength(){
        return originalBytes.length > 2 ? true : false ;
    }

    //判定是否有拆包 是 返回true， 否返回false
    public boolean checkMutilPackages(){

        //TODO 根据实际控制位的D6位的0和1进行判定 [此处伪代码]

        if(true){
            byteCacheList = new ArrayList<>() ;
            processStatus = "go on" ;
            return true ;
        }

        return false ;
    }

    // 判定是否是失效报文 true是，  false不是  [控制域的D4～D5进行判定  为0，表示本次传输服务失败]
    public boolean checkFailureMessage(){

        //TODO 控制域的D4～D5进行判定  为0，表示本次传输服务失败 [此处伪代码]
        return false ;
    }

    //bytes 只截取用户数据区 控制域A + 地址域A + 用户数据 ,CRC也只做用户数据区的校验
    public boolean checkCRC(byte[] bytes){

    //TODO 根据 X7+X6+X5+X2+1 进行判定 [此处伪代码]

       return true ;
    }

    //拆包的情况下，是否是最后一包 true 是， false 否
    public boolean checkLastPackage(){

        //TODO 根据 控制位D6 为1 的后一个字节进行判定 [此处伪代码]
        return true ;
    }

    @Override
    //bytes 只截取用户数据区 控制域A + 地址域A + 用户数据 ,CRC也只做用户数据区的校验
    public abstract NyYcContent decode(byte[] simpleBytes) ;


    //入参为原始接入的字节数组
    public NyYcContent decodeImpl(){

        if(checkDir() && checkMinLength() && checkFailureMessage() && checkMutilPackages()){
            return null ;
        }

        //如果是多报，则进行合并，
        if(checkLastPackage()){
            // TODO 根据byteCacheList 进行多报合并
        }

        //截取有效位数进行操作
        byte[] newBytes = new byte[this.originalBytes[1]] ;
        System.arraycopy(this.originalBytes,4,newBytes,0,newBytes.length);

        if(checkCRC(newBytes)){
            return decode(newBytes) ;
        }

        return null ;
    }
}
