package com.demo.nacos.consumer.entity;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author qiaohui
 * @date 2020/11/24 12:10
 * 获取RedissonClient连接类
 */

@Component
public class RedissonConnector {
    RedissonClient redisson;
    @PostConstruct
    public void init(){
        redisson = Redisson.create();
    }

    public RedissonClient getRedissonClient(){
        return redisson;
    }

}