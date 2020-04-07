package com.gp.homework.netty.nettyone;

import cn.hutool.core.util.StrUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.UnsupportedEncodingException;
import java.net.SocketAddress;

public class NettyOneTimeClient {

    public static void main(String[] args) {
        EventLoopGroup work = new NioEventLoopGroup(1) ;
        Bootstrap client = new Bootstrap() ;
        client.group(work)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                        //测试单包客户端
                       // socketChannel.pipeline().addLast(new NettyOneTimeClientHanlder()) ;


                        //测试粘包客户端
                        socketChannel.pipeline().addLast(new NettyLPackageTimeClientHanlder()) ;

                    }
                }) ;

        try {
            ChannelFuture future = client.connect("127.0.0.1",8081).sync() ;
            future.channel().closeFuture().sync() ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            work.shutdownGracefully() ;
        }

    }
}

class NettyOneTimeClientHanlder extends ChannelInboundHandlerAdapter{
    private final ByteBuf firstMessage ;

    public NettyOneTimeClientHanlder() throws UnsupportedEncodingException {
        byte[] req = "QUERY TIME ORDER".getBytes("UTF-8") ;
        firstMessage = Unpooled.buffer(req.length) ;
        firstMessage.writeBytes(req) ;
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        ByteBuf buffer = (ByteBuf) msg ;
        byte[] bytes = new byte[buffer.readableBytes()] ;
        buffer.readBytes(bytes) ;

        System.out.println(StrUtil.format("Now is :{}", new String(bytes, "utf-8")));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


}


class NettyLPackageTimeClientHanlder extends ChannelInboundHandlerAdapter{
    private int counter ;
    private byte[] req ;

    public NettyLPackageTimeClientHanlder() {
        try {
            req = ("QUERY TIME ORDER"+System.lineSeparator()).getBytes("UTF-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        ByteBuf firstMessage ;
        for(int i=0; i<100; i++){
            firstMessage = Unpooled.buffer(req.length) ;
            firstMessage.writeBytes(req) ;
            ctx.writeAndFlush(firstMessage) ;
        }
//        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        ByteBuf buffer = (ByteBuf) msg ;
        byte[] bytes = new byte[buffer.readableBytes()] ;
        buffer.readBytes(bytes) ;
        System.out.println(StrUtil.format("Now is :{} ;the counter is :{}", new String(bytes, "utf-8"),++counter));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


}