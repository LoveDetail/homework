package com.gp.homework.netty.nettydemo.linebase;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Objects;

/**
 * Create by Wayne on 2020/4/7
 *
 * 通过LineBasedFrameDecoder来解决粘包，半包问题
 *
 *
 *
 */
public class LineBaseFrameTimeServer {

    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup(1) ;
        EventLoopGroup work = new NioEventLoopGroup(3) ;
        ServerBootstrap server = new ServerBootstrap() ;
        server.group(boss,work)
                .channel(NioServerSocketChannel.class)

                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {

                        //读到换行符标识为一行的结尾，1024为读取的最大限制，超出1024字节后，报异常
                        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024)) ;
                        socketChannel.pipeline().addLast(new StringDecoder()) ;
                        socketChannel.pipeline().addLast(new LineBaseTimeServerHanlder()) ;
                    }
                })
                .option(ChannelOption.SO_BACKLOG,1024)
                .childOption(ChannelOption.SO_KEEPALIVE,true) ;

        try {
           ChannelFuture future = server.bind(8080).sync() ;
           future.channel().closeFuture().sync() ;
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            boss.shutdownGracefully() ;
            work.shutdownGracefully() ;
        }
    }

}


class LineBaseTimeServerHanlder extends ChannelInboundHandlerAdapter{

    int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String result = (String)msg ;

        System.out.println(StrUtil.format("The time server receive order : {} ;the counter is : {}",result,++counter));

        String currentTime = Objects.equals(result,"QUERY TIME ORDER") ? DateUtil.formatDateTime(Calendar.getInstance().getTime()) : "BAD ORDER" ;
        currentTime = currentTime + System.lineSeparator() ;

        ByteBuf buffer = Unpooled.copiedBuffer(currentTime.getBytes(StandardCharsets.UTF_8)) ;
        ctx.writeAndFlush(buffer) ;

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close() ;
    }
}

