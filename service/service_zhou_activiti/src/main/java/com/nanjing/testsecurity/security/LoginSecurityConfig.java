package com.nanjing.testsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//登录配置
@Configuration
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {
    //登录成功类
    @Autowired
    private LoginSuccess loginSuccess;

    //登录失败类
    @Autowired
    private LoginFailure loginFailure;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                //登录的方法
                .loginPage("/login")
                .loginProcessingUrl("/login")
                //登录成功：
                .successHandler(loginSuccess)
                //登录失败：
                .failureHandler(loginFailure)
                .and()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();

    }
}
