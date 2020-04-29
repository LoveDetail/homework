package com.gp.homework.netty.nettydemo.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * Create by Wayne on 2020/4/10
 */
public class NettyUDPClient {
    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup(1) ;
        Bootstrap bootstrap = new Bootstrap() ;
        bootstrap.group(boss)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST,true)
                .handler(new NettyUDPClientHandler()) ;

        try {
            Channel channel = bootstrap.bind(0).channel() ;
            channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("查询",CharsetUtil.UTF_8),
                    new InetSocketAddress("127.0.0.1",8800)))
                    .sync() ;

            if(!channel.closeFuture().await(15000)){
                System.out.println("查询超时");
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            boss.shutdownGracefully() ;
        }
    }


}

class NettyUDPClientHandler extends SimpleChannelInboundHandler<DatagramPacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        String result = datagramPacket.content().toString(CharsetUtil.UTF_8) ;

        if(result.startsWith("检索的诗句是：")){
            System.out.println(result);
        }
        else
            System.out.println("没有结果");

        channelHandlerContext.close() ;
    }
}