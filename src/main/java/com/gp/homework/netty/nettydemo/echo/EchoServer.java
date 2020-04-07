package com.gp.homework.netty.nettydemo.echo;

import cn.hutool.core.util.StrUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LoggingHandler;

import java.nio.charset.StandardCharsets;

/**
 * Create by Wayne on 2020/4/7
 */
public class EchoServer {

//分隔符解码器服务端
    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup(1) ;
        EventLoopGroup work = new NioEventLoopGroup(2) ;
        ServerBootstrap server = new ServerBootstrap() ;
        server.group(boss,work)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1204)
                .handler(new LoggingHandler())
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                        ByteBuf delimiter = Unpooled.copiedBuffer(  "$_".getBytes(StandardCharsets.UTF_8)) ;
                        socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter)) ;
                        socketChannel.pipeline().addLast(new StringDecoder()) ;
                        socketChannel.pipeline().addLast(new EchoServerHanlder()) ;
                    }
                }) ;
        try {
            ChannelFuture future = server.bind(8080).sync();
            future.channel().closeFuture().sync() ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            work.shutdownGracefully() ;
            boss.shutdownGracefully() ;
        }

    }

}

class EchoServerHanlder extends ChannelInboundHandlerAdapter{
    private int counter ;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg ;
        System.out.println(StrUtil.format("This is {},Time recive client [{}]",++counter,body));

        body += "$_" ;

        ByteBuf message = Unpooled.copiedBuffer(body.getBytes(StandardCharsets.UTF_8)) ;
        ctx.writeAndFlush(message) ;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close() ;
    }
}