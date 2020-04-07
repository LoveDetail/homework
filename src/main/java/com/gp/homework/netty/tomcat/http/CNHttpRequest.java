package com.gp.homework.netty.tomcat.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * Create by Wayne on 2020/3/23
 */
public class CNHttpRequest {

    ChannelHandlerContext context ;
    HttpRequest httpRequest ;

    public CNHttpRequest(ChannelHandlerContext context, HttpRequest httpRequest) {
        this.context = context;
        this.httpRequest = httpRequest;
    }


    public String getUrl(){
        return httpRequest.uri() ;
    }

    public String getMethod(){
        return httpRequest.method().name() ;
    }

    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(httpRequest.uri());
        return decoder.parameters();
    }

    public String getParameter(String name) {

        Map<String, List<String>> params = getParameters();
        List<String> param = params.get(name);
        if (null == param) {
            return null;
        } else {
            return param.get(0);
        }
    }

}
