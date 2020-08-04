package com.gp.homework.controller.springsession;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Create by Wayne on 2020/8/4
 */

@RestController
public class HelloSessionController {
    @Value("${server.port}")
    Integer port;

    @GetMapping("/set")
    public String set(HttpSession session) {
        session.setAttribute("user", "javaboy");
        return String.valueOf(port);
    }

    @GetMapping("/get")
    public String get(HttpSession session) {

        return StrUtil.format("{}:{}", session.getAttribute("user"), port);

//        return session.getAttribute("user") + ":" + port;
    }









}
