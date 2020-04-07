package com.gp.homework.netty.tomcat;

import com.gp.homework.netty.tomcat.http.CNHttpRequest;
import com.gp.homework.netty.tomcat.http.CNHttpResponse;
import com.gp.homework.netty.tomcat.http.CNHttpServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Create by Wayne on 2020/3/23
 */
public class HttpTomcat {

    private int port = 8080 ;
    private Map<String,CNHttpServlet> servletMap = new HashMap<>() ;
    private Properties webxml = new Properties() ;


    //初始化配置文件
    public void init() {

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(this.getClass().getResource("/").getPath()+"web.properties");
            webxml.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


        webxml.forEach((k,v)->{

            String key = k.toString() ;

            if(key.endsWith(".url")){
                String servletName = key.replaceAll("\\.url$", "");
                String url = webxml.getProperty(key);
                String className = webxml.getProperty(servletName + ".className");
                CNHttpServlet obj = null;
                try {
                    obj = (CNHttpServlet) Class.forName(className).newInstance();
                }
                catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                servletMap.put(url, obj);
            }
        });


//        for (Object k : webxml.keySet()) {
//
//            String key = k.toString();
//            if(key.endsWith(".url")){
//                String servletName = key.replaceAll("\\.url$", "");
//                String url = webxml.getProperty(key);
//                String className = webxml.getProperty(servletName + ".className");
//                CNHttpServlet obj = null;
//                try {
//                    obj = (CNHttpServlet) Class.forName(className).newInstance();
//                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//                servletMap.put(url, obj);
//            }
//        }

    }

    public void start()  {

        init();
        ServerBootstrap serverBootstrap = new ServerBootstrap() ;
        EventLoopGroup boss = new NioEventLoopGroup(1) ;
        EventLoopGroup work = new NioEventLoopGroup() ;

        serverBootstrap.group(boss,work).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {


                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

//                        socketChannel.pipeline().
//                                addLast("frameDecoder",new LengthFieldBasedFrameDecoder(1024, 0, 2,0,2)) ;
                        socketChannel.pipeline().addLast(new HttpResponseEncoder()) ;
                        socketChannel.pipeline().addLast(new HttpRequestDecoder()) ;
                        socketChannel.pipeline().addLast(new GPTomcatHandler()) ;

                    }
                })
                // 针对主线程的配置 分配线程最大数量 128
                .option(ChannelOption.SO_BACKLOG, 128)
                // 针对子线程的配置 保持长连接
                .childOption(ChannelOption.SO_KEEPALIVE, true);


        try {
            ChannelFuture f = serverBootstrap.bind(port).sync();
            System.out.println("GP Tomcat 已启动，监听的端口是：" + port);
            f.channel().closeFuture().sync() ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            boss.shutdownGracefully() ;
            work.shutdownGracefully() ;
        }



    }

    public class GPTomcatHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof HttpRequest){
                HttpRequest req = (HttpRequest) msg;

                // 转交给我们自己的request实现
                CNHttpRequest request = new CNHttpRequest(ctx,req);

                // 转交给我们自己的response实现
                CNHttpResponse response = new CNHttpResponse(ctx,req);

                // 实际业务处理
                String url = request.getUrl();

                if(servletMap.containsKey(url)){
                    servletMap.get(url).service(request, response);
                }else{
                    response.write("404 - Not Found");
                }

            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        }
    }


    public static void main(String[] args) {
        new HttpTomcat().start();
    }


}
