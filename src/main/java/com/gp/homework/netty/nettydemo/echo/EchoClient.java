package com.gp.homework.netty.nettydemo.echo;

import cn.hutool.core.util.StrUtil;
import com.gp.homework.common.util.ByteUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.TimeUnit;

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

//                        ByteBuf flag = Unpooled.copiedBuffer("$_".getBytes(StandardCharsets.UTF_8)) ;


//                        socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,flag)) ;
//                        socketChannel.pipeline().addLast(new StringDecoder()) ;
                        socketChannel.pipeline().addLast(new EchoClientHanlder()) ;

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

class EchoClientHanlder extends ChannelInboundHandlerAdapter{
    private int counter ;
//    private byte[] req = "Hi Wayne,Welcome to Netty.$_".getBytes(StandardCharsets.UTF_8) ;
//    private byte[] req1 = ByteUtil.hexStringToBytes("7E7E010030960357123432002E020560200323200033F1F1003096035747F0F020032320000E1A0049960311016745200000048038121394FF02082703CD6D") ;

    private byte[] req1 = ByteUtil.hexStringToBytes("7E7E01003096035700002F000802001C20042223595903617A") ;
//   private byte[] req2 = ByteUtil.hexStringToBytes("7E7E1000309603571234370082022A72200422145007F1F1003096035748F0F02004221450F460000000000000000000000000F5C0FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF261900000020190000001A19000000391AFFFFFF35110000331001180219FF0266181102390619000019FF0619000006FF07121380FF08100000FF091000003812134203C221") ;
 private byte[] req3 = ByteUtil.hexStringToBytes("7E 7E 01 00 30 96 03 57 00 00 4A 00 0F 02 01 7A 20 06 23 10 28 12 F1 F1 00 30 96 03 57 03 68 BC") ;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//       for(int i=0; i<100; i++){
//           ByteBuf messate = Unpooled.buffer(req.length) ;
//           messate.writeBytes(req) ;
           ctx.writeAndFlush(Unpooled.copiedBuffer(req1)) ;
           TimeUnit.SECONDS.sleep(2);
           ctx.writeAndFlush(Unpooled.copiedBuffer(req3)) ;
//       }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        String body = (String)msg ;



        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
//        String body = new String(req, "UTF-8");


        System.out.println(StrUtil.format("channelRead==> {}",ByteUtil.bytesToHexString(req,0,req.length,"")));
        ctx.flush() ;ctx.close() ;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close() ;
    }
}
