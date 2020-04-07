package com.gp.homework.netty.nio;

import cn.hutool.core.date.DateUtil;
import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TimeClentHanldle implements Runnable {

    private String host ;
    private int port ;
    private Selector selector ;
    private SocketChannel socketChannel ;
    private volatile boolean stop ;

    public TimeClentHanldle(String host,int port) throws IOException {
        this.host = host ;
        this.port = port ;
        selector = Selector.open() ;
        socketChannel = SocketChannel.open() ;
        socketChannel.configureBlocking(false) ;
    }

    @Override
    public void run() {

        try {
            doConnect() ;
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }


        while (!stop){
            try {
                selector.select(1000) ;
                Set<SelectionKey> set = selector.selectedKeys() ;
                Iterator<SelectionKey> iterator = set.iterator() ;

                SelectionKey key = null ;

                while (iterator.hasNext()){
                    key = iterator.next() ;
                    iterator.remove();


                    handleInput(key);


                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void doConnect() throws IOException {
        if(socketChannel.connect(new InetSocketAddress(host,port))){
            socketChannel.register(selector,SelectionKey.OP_READ) ;
            doWrite(socketChannel) ;
        }
        else
            socketChannel.register(selector,SelectionKey.OP_CONNECT) ;

    }

    private void doWrite(SocketChannel socketChannel) throws IOException {
        byte[] req = "QUERY TIME ORDER".getBytes() ;
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length) ;
        writeBuffer.put(req) ;
        writeBuffer.flip() ;
        socketChannel.write(writeBuffer) ;

        if(!writeBuffer.hasRemaining())
            System.out.println("Send order 2 server succeed.");

    }

    private void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            SocketChannel socketChannel = (SocketChannel) key.channel() ;
            if(key.isConnectable()){
                if(socketChannel.finishConnect()){
                    socketChannel.register(selector,SelectionKey.OP_READ) ;
                    doWrite(socketChannel) ;
                }
                else System.exit(1);
            }

            if(key.isReadable()){
                ByteBuffer readByffer = ByteBuffer.allocate(1024) ;
                int readBytes = socketChannel.read(readByffer) ;
                if(readBytes > 0){
                    readByffer.flip() ;
                    byte[] bytes = new byte[readByffer.remaining()] ;
                    readByffer.get(bytes) ;
                    String body = new String(bytes,"utf-8") ;
                    System.out.println("Now is :"+body);
                    this.stop = true ;
                }
                else if(readBytes < 0){
                    key.cancel();
                    socketChannel.close();
                }
                else ;
            }
        }



    }

}
