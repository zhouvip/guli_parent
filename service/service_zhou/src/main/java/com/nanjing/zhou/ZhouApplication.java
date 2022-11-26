package com.nanjing.zhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yimo
 * @version 1.0
 * @date 2022/3/26 15:54
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.nanjing"})
public class ZhouApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZhouApplication.class,args);
    }
}
