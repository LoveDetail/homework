package com.gp.homework.netty.oio;

import cn.hutool.core.date.DateUtil;
import lombok.Cleanup;

import java.io.*;
import java.net.Socket;
import java.util.Calendar;

public class TimeServerHanlder implements Runnable {

    private Socket socket ;
    public TimeServerHanlder(Socket socket){this.socket = socket;}

    @Override
    public void run() {

        try {
            @Cleanup
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream())) ;

            @Cleanup
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(),true) ;


            String currentTime = "" ;
            String body = null ;
            while (true){
                body = in.readLine() ;
                if(body == null)
                    break;

                System.out.println("The time server recive order:" + body);

                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                        DateUtil.formatDateTime(Calendar.getInstance().getTime()) : "BAD ORDER" ;

                out.println(currentTime);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.socket = null ;
        }

    }
}
