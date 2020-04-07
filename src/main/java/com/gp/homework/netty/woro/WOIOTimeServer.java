package com.gp.homework.netty.woro;

import com.gp.homework.netty.oio.TimeServerHanlder;
import lombok.Cleanup;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WOIOTimeServer {


    public static void main(String[] args) {
        int port = 8080 ;

        try {
            @Cleanup
            ServerSocket server = new ServerSocket(port) ;
            System.out.println("the time server is start in port: " + port);

            TimeServerHandlerExecutePool pool =
                    new TimeServerHandlerExecutePool(50,10000) ;

            while (true){
                pool.execute(new TimeServerHanlder(server.accept()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
