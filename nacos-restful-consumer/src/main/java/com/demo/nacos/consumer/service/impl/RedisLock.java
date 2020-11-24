package com.demo.nacos.consumer.service.impl;

import com.demo.nacos.consumer.entity.RedissonConnector;
import com.demo.nacos.consumer.service.AquiredLockWorker;
import com.demo.nacos.consumer.service.DistributedLocker;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author qiaohui
 * @date 2020/11/24 11:57
 * 实现分布式接口的类
 * Redssion 实现分布式锁的流程主要是五个步骤
 * 导入pom文件， 编写一个获取分布式锁接口， 定义一个分布式锁的管理接口， 定义一个类用来实现刚才定义分布式接口管理， 定义一个没有获取到分布式锁的异常
 */
@Component
public class RedisLock implements DistributedLocker {

    private final static String name = "redisLock";

    @Autowired
    private RedissonConnector redissonConnector;


    @Override
    public <T> T lock(String resourceName, AquiredLockWorker<T> worker) throws Exception {
        return lock(resourceName, worker, 100);
    }

    @Override
    public <T> T lock(String resourceName, AquiredLockWorker<T> worker, long time) throws Exception {
        RedissonClient redissonClient = redissonConnector.getRedissonClient();
        RLock lock = redissonClient.getLock(name + resourceName);
        //等待100秒释放锁
        boolean flag = lock.tryLock(100, time, TimeUnit.SECONDS);
        if (flag) {
            //代码必须这样设计
            try {
                //拿取到锁之后执行的业务的方法
                return worker.invokeAfterLockAquire();
            } finally {
                lock.unlock();
            }
        }

        //没有拿取到锁时，会报没有拿取锁异常
        throw new UnsupportedOperationException();
    }
}
