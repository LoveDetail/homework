package com.gp.homework.netty.nettydemo.echo;

import cn.hutool.core.util.StrUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.StandardCharsets;

/**
 * Create by Wayne on 2020/4/7
 */
public class EchoClient {
    //分隔符解码器客户端
    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap() ;
        bootstrap.group(boss)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                        ByteBuf flag = Unpooled.copiedBuffer("$_".getBytes(StandardCharsets.UTF_8)) ;


                        socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,flag)) ;
                        socketChannel.pipeline().addLast(new StringDecoder()) ;
                        socketChannel.pipeline().addLast(new EchoClientHanlder()) ;

                    }
                }) ;

        try {
            ChannelFuture future = bootstrap.connect("localhost",8080).sync() ;
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

class EchoClientHanlder extends ChannelInboundHandlerAdapter{
    private int counter ;
    private byte[] req = "Hi Wayne,Welcome to Netty.$_".getBytes(StandardCharsets.UTF_8) ;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       for(int i=0; i<100; i++){
//           ByteBuf messate = Unpooled.buffer(req.length) ;
//           messate.writeBytes(req) ;
           ctx.writeAndFlush(Unpooled.copiedBuffer(req)) ;
       }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String)msg ;
        System.out.println(StrUtil.format("This is {},times recive server:[{}]",++counter,body));
        ctx.flush() ;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close() ;
    }
}
