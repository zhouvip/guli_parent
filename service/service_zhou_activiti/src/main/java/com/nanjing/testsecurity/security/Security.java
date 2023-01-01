package com.nanjing.testsecurity.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class Security {

    private Logger logger = LoggerFactory.getLogger(Security.class);

    @Autowired
    @Qualifier("myUserDetailsService")
    private UserDetailsService userDetailsService;

    public void logInAs(String username){
        UserDetails user = userDetailsService.loadUserByUsername(username);
        if (user == null) {
            throw  new IllegalStateException("用户"+username+"不存在！");
        }
        logger.info(">以身份登录:"+username);
        SecurityContextHolder.setContext(new SecurityContextImpl(new Authentication() {
            //获取权限
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return user.getAuthorities();
            }

            //获取凭据
            @Override
            public Object getCredentials() {
                return user.getPassword();
            }

            //获取详细信息
            @Override
            public Object getDetails() {
                return user;
            }

            //获取委托人
            @Override
            public Object getPrincipal() {
                return user;
            }

            //已通过身份验证
            @Override
            public boolean isAuthenticated() {
                return true;
            }

            //设置已验证
            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            //获取用户名称
            @Override
            public String getName() {
                return user.getUsername();
            }
        }));
    }
}

