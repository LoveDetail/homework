package com.gp.homework.netty.oio;

import lombok.Cleanup;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {


    public static void main(String[] args) {
        int port = 8080 ;

        try {
            @Cleanup
            ServerSocket serverSocket = new ServerSocket(port) ;
            System.out.println("the time server is start in port: " + port);


            while (true){

                @Cleanup
                Socket socket = serverSocket.accept() ;
                new Thread(new TimeServerHanlder(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
