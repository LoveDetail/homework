package com.gp.homework;

import lombok.Cleanup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Create by Wayne on 2020/4/16
 */
public class NettyTestJava {


    public static void main(String[] args) throws IOException {
       @Cleanup
       FileInputStream inputStream = new FileInputStream(new File("e:\\clearLog.txt")) ;
        @Cleanup
        FileChannel fileChannel = inputStream.getChannel() ;

        ByteBuffer buffer = ByteBuffer.allocate(1024) ;


        fileChannel.read(buffer) ;
        output("readBuffer",buffer);

        buffer.flip() ;
        output("flipBuffer",buffer);


        while (buffer.remaining() > 0) {
            byte b = buffer.get();
        }
        output("getBuffer",buffer);


        buffer.clear() ;
        output("clearBuffer",buffer);





    }

    //把这个缓冲里面实时状态给答应出来
    public static void output(String step, ByteBuffer buffer) {
        System.out.println(step + " : ");
        System.out.print("capacity: " + buffer.capacity() + ", ");
        System.out.print("position: " + buffer.position() + ", ");
        System.out.println("limit: " + buffer.limit());
        System.out.println();
    }
}
