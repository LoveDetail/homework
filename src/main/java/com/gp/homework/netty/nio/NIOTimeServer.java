package com.gp.homework.netty.nio;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.gp.homework.netty.woro.TimeServerHandlerExecutePool;
import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

public class NIOTimeServer {



    public static void main(String[] args) {
        int port = 8080 ;

        MultipexerTimeServer timeServer = new MultipexerTimeServer(port) ;
        new Thread(timeServer,"NIO-MultipexerTimeServer-001").start();
    }




}

class MultipexerTimeServer implements Runnable{

    private Selector selector ;
    private ServerSocketChannel serverSocketChannel ;
    private volatile boolean stop = false;


    public MultipexerTimeServer(int port){

        try {
            selector = Selector.open() ;
            serverSocketChannel = ServerSocketChannel.open() ;
            serverSocketChannel.configureBlocking(false) ;
            serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT) ;

            System.out.println("the time server is start in port: " + port);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    public void stop(){
        this.stop = true ;
    }

    private void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            if(key.isAcceptable()){
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel() ;
                SocketChannel socketChannel = serverSocketChannel.accept() ;
                socketChannel.configureBlocking(false) ;
                socketChannel.register(selector,SelectionKey.OP_READ) ;
            }

            if(key.isReadable()){
                SocketChannel socketChannel = serverSocketChannel.accept() ;
                ByteBuffer buffer = ByteBuffer.allocate(1024) ;
                int readbytes = socketChannel.read(buffer) ;

                if(readbytes > 0){
                    buffer.flip() ;
                    byte[] bytes = new byte[readbytes] ;
                    String body = new String(bytes,"UTF-8") ;

                    System.out.println("The time server recive order:" + body);

                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                            DateUtil.formatDateTime(Calendar.getInstance().getTime()) : "BAD ORDER" ;

                    doWrite(socketChannel,currentTime) ;
                }
                else if(readbytes < 0){
                    key.cancel();
                    socketChannel.close();
                }
            }

        }
    }


    private void doWrite(SocketChannel socketChannel,String body){

        if(!StrUtil.isEmpty(body)){
            byte[] bytes = body.getBytes() ;
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes) ;
            buffer.flip() ;
            try {
                socketChannel.write(buffer) ;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }



    @Override
    public void run() {

        while(!stop){


            try {
                selector.select(1000) ;
                Set<SelectionKey> set = selector.selectedKeys() ;
                Iterator<SelectionKey> iterator = set.iterator() ;
                SelectionKey key = null ;

                while (iterator.hasNext()){
                    key = iterator.next() ;
                    iterator.remove();


                    try {
                        handleInput(key);
                    }
                    catch (Exception e){
                        if(key != null){
                            key.cancel();
                            if(key.channel() != null)
                                key.channel().close();
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
}
