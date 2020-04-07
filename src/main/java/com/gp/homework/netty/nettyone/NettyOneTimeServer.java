package com.gp.homework.netty.nettyone;

import cn.hutool.core.date.DateUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.util.Calendar;

public class NettyOneTimeServer {


    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup() ;
        EventLoopGroup work = new NioEventLoopGroup() ;

        ServerBootstrap server = new ServerBootstrap() ;
        server.group(boss,work)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                        //测试单个netty服务器端
                        //socketChannel.pipeline().addLast(new NettyOneTimeServerHanlder()) ;

                        //测试粘包服务器端
                        socketChannel.pipeline().addLast(new NettyLpackageTimeServerHanlder()) ;

                    }
                })
                .childOption(ChannelOption.AUTO_READ, true)
                .option(ChannelOption.SO_BACKLOG,1024) ;

        try {
            ChannelFuture future = server.bind(8081).sync() ;
            future.channel().closeFuture().sync() ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            boss.shutdownGracefully() ;
            work.shutdownGracefully() ;
        }


    }
}

class NettyOneTimeServerHanlder extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf) msg ;
        byte[] bytes = new byte[buffer.readableBytes()] ;
        buffer.readBytes(bytes) ;

        String body = new String(bytes,"UTF-8") ;
        System.out.println("The time server recive order:" + body);

        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                DateUtil.formatDateTime(Calendar.getInstance().getTime()) : "BAD ORDER" ;

       ctx.writeAndFlush(Unpooled.copiedBuffer(currentTime.getBytes()) ) ;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close() ;
    }
}


//测试粘包，拆包
class NettyLpackageTimeServerHanlder extends ChannelInboundHandlerAdapter{

    private int counter ;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf) msg ;
        byte[] bytes = new byte[buffer.readableBytes()] ;
        buffer.readBytes(bytes) ;

        String body = new String(bytes,"UTF-8").
                substring(0,bytes.length-System.lineSeparator().length()) ;

        System.out.println("The time server recive order:" + body+" ; the counter is :"+ ++counter);

        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                DateUtil.formatDateTime(Calendar.getInstance().getTime()) : "BAD ORDER" ;

        //封装成ByteBuf对象返回给channel
        ByteBuf buf = Unpooled.copiedBuffer(currentTime.getBytes()) ;

        ctx.writeAndFlush(Unpooled.copiedBuffer(buf) ) ;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close() ;
    }
}



