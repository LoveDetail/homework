package com.gp.homework.netty.nettydemo.seri;

import cn.hutool.core.util.StrUtil;
import com.gp.homework.netty.nettydemo.seri.entity.SimplePeople;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class SubReqClient {

    public static void main(String[] args) {
        EventLoopGroup work = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap() ;
        bootstrap.group(work)
                .option(ChannelOption.TCP_NODELAY,true)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                        socketChannel.pipeline().addLast(new ObjectDecoder(1024,
                                ClassResolvers.cacheDisabled(this.getClass().getClassLoader()))) ;
                        socketChannel.pipeline().addLast(new ObjectEncoder()) ;
                        socketChannel.pipeline().addLast(new SubReqClientHanlder()) ;
                    }
                }) ;
        try {
            ChannelFuture future = bootstrap.connect("127.0.0.1",8082).sync() ;
            future.channel().closeFuture().sync() ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            work.shutdownGracefully() ;
        }


    }
}


class SubReqClientHanlder extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i=0; i<10; i++){
            ctx.write(request(i)) ;
        }
        ctx.flush() ;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(StrUtil.format("Receive server response :[{}]", msg));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush() ;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       ctx.close() ;
    }

    private SimplePeople request(int i){
        return new SimplePeople()
                .setAddress("北京市平谷区")
                .setPhotoNumber("13466765567")
                .setProductName("Netty 权威指南")
                .setSubReqId(i)
                .setUserName("Wayne") ;
    }

}