package com.nanjing.edu.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author yimo
 * @version 1.0
 * @date 2022/3/19 15:28
 */
@Configuration
@MapperScan("com.nanjing.eduservice.mapper")
public class EduConfig {

    /**
     * SQL执行效率插件
     *
     * @return
     */
    @Bean
    @Profile({"dev","test"})// 指定环境为dev test生效
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor interceptor = new PerformanceInterceptor();
        // sql美化打印
        interceptor.setFormat(true);
        // 设置SQL超时时间
        interceptor.setMaxTime(1000);//ms，超过此处设置的ms则sql不执行
        return interceptor;
    }

    /**
     * 逻辑删除插件
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
