package com.gp.homework.netty.nettydemo.seri;

import cn.hutool.core.util.StrUtil;
import com.gp.homework.netty.nettydemo.seri.entity.SimplePeople;
import com.gp.homework.netty.nettydemo.seri.entity.SimpleResp;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class SubReqServer {

    public static void main(String[] args) {

        EventLoopGroup boss = new NioEventLoopGroup(1) ;
        EventLoopGroup work = new NioEventLoopGroup(2) ;

        ServerBootstrap server = new ServerBootstrap() ;

        server.group(boss,work)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ObjectDecoder(1024*1024,
                                ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader()))) ;

                        socketChannel.pipeline().addLast(new ObjectEncoder()) ;
                        socketChannel.pipeline().addLast(new SubReqServerHanlder()) ;
                    }
                }) ;
        try {
            ChannelFuture future = server.bind(8082).sync() ;
            future.channel().closeFuture().sync() ;
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            work.shutdownGracefully() ;
            boss.shutdownGracefully() ;
        }

    }
}

class SubReqServerHanlder extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SimplePeople people = (SimplePeople)msg ;
        if("Wayne".equals(people.getUserName())){
            System.out.println(StrUtil.format("Server accept client subcrib req : [{}]",people.toString()));
            ctx.writeAndFlush(resp(people.getSubReqId())) ;
        }
    }

    private SimpleResp resp(int respId){
        SimpleResp resp =   new SimpleResp() ;
        resp.setSubReqId(respId);
        resp.setRespCode(0);
        resp.setDesc("Netty book order succeed,3 days later,send to the designated address");
        return resp ;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
