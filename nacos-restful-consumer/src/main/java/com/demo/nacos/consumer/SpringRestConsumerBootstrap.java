package com.demo.nacos.consumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qiaohui
 * @date 2020/9/24 10:48
 */
@EnableDubbo
@SpringBootApplication
public class SpringRestConsumerBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(SpringRestConsumerBootstrap.class, args);
    }
}
