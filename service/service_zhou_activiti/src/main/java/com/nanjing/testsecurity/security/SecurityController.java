package com.nanjing.testsecurity.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//用户登录控制器
@RestController
@RequestMapping("/hello")
public class SecurityController {

    @RequestMapping("/login")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        return new String("需要登录！");
    }
}

