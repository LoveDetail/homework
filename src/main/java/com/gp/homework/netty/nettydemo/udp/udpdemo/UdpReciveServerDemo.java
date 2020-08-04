package com.gp.homework.netty.nettydemo.udp.udpdemo;

import com.gp.homework.common.HexUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * Create by Wayne on 2020/4/26
 */
public class UdpReciveServerDemo {


    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup(1) ;
        Bootstrap bootstrap = new Bootstrap().group(boss) ;

        bootstrap.channel(NioDatagramChannel.class)
                .handler(new NettyUPDDemoServerHandler())
                .option(ChannelOption.SO_BROADCAST,true) ;

        try {
            bootstrap.bind(9900).sync().channel().closeFuture().await() ;
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            boss.shutdownGracefully() ;
        }
    }

}

class NettyUPDDemoServerHandler extends SimpleChannelInboundHandler<DatagramPacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {



        ByteBuf temp = datagramPacket.content() ;

        byte[] realByte = new byte[temp.readableBytes()] ;
        temp.readBytes(realByte) ;

        byte[] realByteDemo = {0,0,0,realByte[3]} ;
        System.out.println(HexUtil.byteArrayToInt(realByteDemo));
        System.out.println(HexUtil.myByteArray2Int(realByteDemo));

//        Function<Integer,BigDecimal> function  = (paragrame)->
//                new BigDecimal((paragrame-400) / 10).setScale(2,BigDecimal.ROUND_HALF_UP) ;
//
//        System.out.println(mathRandom(function, HexUtil.byteArrayToInt(realByteDemo)));
    }


    private BigDecimal mathRandom(Function<Integer,BigDecimal> function,Integer para){
        return function.apply(para) ;
    }


}
