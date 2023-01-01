package com.nanjing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ：楼兰
 * @date ：Created in 2021/4/8
 * @description:
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class ActApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActApplication.class,args);
    }
}
