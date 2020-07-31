package com.gp.homework.netty.nettydemo.linebase;

import cn.hutool.core.util.StrUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.StandardCharsets;

/**
 * Create by Wayne on 2020/4/7
 */
public class LineBaseFrameTimeClient {

    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup(1) ;
        Bootstrap bootstrap = new Bootstrap() ;
        bootstrap.group(boss)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024)) ;
                        socketChannel.pipeline().addLast(new StringDecoder()) ;
                        socketChannel.pipeline().addLast(new LineBaseFrameTimeHandler()) ;
                    }
                }) ;


        try {
            ChannelFuture future = bootstrap.connect("192.168.2.74",21979).sync() ;
            future.channel().closeFuture().sync() ;
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            boss.shutdownGracefully() ;
        }


    }
}

class LineBaseFrameTimeHandler extends ChannelInboundHandlerAdapter {

    private int counter ;
    private byte[] req = StrUtil.format("QUERY TIME ORDER{}",System.lineSeparator())
            .getBytes(StandardCharsets.UTF_8);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for(int i=0; i<100; i++){
            ByteBuf message = Unpooled.buffer(req.length) ;
            message.writeBytes(req) ;
            ctx.writeAndFlush(message) ;
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = (String)msg ;
        System.out.println(StrUtil.format("Now is :{} ; and counter is:{}",message,++counter));

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       ctx.close() ;
    }
}
