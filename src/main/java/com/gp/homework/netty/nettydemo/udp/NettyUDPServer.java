package com.gp.homework.netty.nettydemo.udp;

import cn.hutool.core.util.StrUtil;
import com.gp.homework.netty.NettyConstant;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Create by Wayne on 2020/4/10
 */
public class NettyUDPServer {

    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup() ;
        Bootstrap bootstrap = new Bootstrap() ;
        bootstrap.group(boss)
                .channel(NioDatagramChannel.class)
                .handler(new NettyUPDServerHandler())
                .option(ChannelOption.SO_BROADCAST,true) ;

        try {
           bootstrap.bind(8800).sync().channel().closeFuture().await() ;
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            boss.shutdownGracefully() ;
        }
    }
}

class NettyUPDServerHandler extends SimpleChannelInboundHandler<DatagramPacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket o) throws Exception {

       String request =  o.content().toString(CharsetUtil.UTF_8) ;
        System.out.println("The recive reqeust is : "+request);
       if(Objects.equals("查询",request)){
           String result = query() ;
           System.out.println("===========> "+result);
           channelHandlerContext.writeAndFlush(
                    new DatagramPacket(Unpooled.copiedBuffer(StrUtil.format("检索的诗句是：{}",result),CharsetUtil.UTF_8),
                            o.sender())) ;
       }
    }

    private static String query(){
        return NettyConstant.POEM[ThreadLocalRandom.current().nextInt(NettyConstant.POEM.length)] ;
    }
}
