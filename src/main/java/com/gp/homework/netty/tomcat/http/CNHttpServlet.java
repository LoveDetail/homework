package com.gp.homework.netty.tomcat.http;

/**
 * Create by Wayne on 2020/3/23
 */
public abstract class CNHttpServlet {
    public void service(CNHttpRequest request, CNHttpResponse response) throws Exception{

        //由service方法来决定，是调用doGet或者调用doPost
        if("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request, response);
        }else{
            doPost(request, response);
        }

    }

    public abstract void doGet(CNHttpRequest request,CNHttpResponse response) throws Exception;

    public abstract void doPost(CNHttpRequest request,CNHttpResponse response) throws Exception;
}

