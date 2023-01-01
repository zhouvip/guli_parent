package com.nanjing.testsecurity.service;

import com.nanjing.testsecurity.mapper.UserInfoBeanMapper;
import com.nanjing.testsecurity.pojo.UserInfoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserInfoBeanMapper userInfoBeanMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查库，完成登录
        UserInfoBean userInfoBean = userInfoBeanMapper.selectByUsername(username);
        if (userInfoBean == null) {
            throw new UsernameNotFoundException("数据库中无此用户");
        }

        return userInfoBean;
    }

    //加密方法
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

