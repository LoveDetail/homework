package com.gp.homework.netty.nio;

import com.gp.homework.netty.oio.TimeServerHanlder;
import lombok.Cleanup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NIOTimeClient {

    public static void main(String[] args) throws IOException {
        int port = 8080 ;

        new Thread(new TimeClentHanldle("127.0.0.1",port)).start();


    }
}
