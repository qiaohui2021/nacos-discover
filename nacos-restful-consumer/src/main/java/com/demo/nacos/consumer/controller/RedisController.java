package com.demo.nacos.consumer.controller;

import com.demo.nacos.consumer.service.AquiredLockWorker;
import com.demo.nacos.consumer.service.DistributedLocker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiaohui
 * @date 2020/11/24 12:03
 */
@RestController
public class RedisController {

    @Autowired
    private DistributedLocker distributedLocker;

    @RequestMapping(value = "index")
    public String index() throws Exception {
        distributedLocker.lock("test", new AquiredLockWorker<Object>(){

            @Override
            public Object invokeAfterLockAquire() throws Exception {
                System.out.println("这里直接进行逻辑处理");
                Thread.sleep(100);
                return null;
            }
        });

        return "hello redis";
    }
}
