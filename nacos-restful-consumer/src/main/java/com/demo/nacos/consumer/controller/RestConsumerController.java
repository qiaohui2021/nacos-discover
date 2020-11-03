package com.demo.nacos.consumer.controller;

import nacos.dubbo.service2.api.Service2Api;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


/**
 * @author qiaohui
 * @date 2020/9/23 16:44
 */
@RestController
public class RestConsumerController {

    //通过负载均衡来发现地址(客户端的负载均衡器)
    @Autowired
    LoadBalancerClient loadBalancerClient;
//    @Value("${provider.address}")
//    private String provider;

    @Reference
    Service2Api service2Api;

    @GetMapping(value = "/service")
    public String service(){
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-restful-provider");
        URI uri = serviceInstance.getUri();
        //String result = restTemplate.getForObject("http://"+provider+"/service",String.class);
        String result = restTemplate.getForObject(uri+"/service",String.class);
        return "consumer invodel" + result;
    }

    @GetMapping(value = "/service2")
    public String service2(){
        String privadeResult = service2Api.dubboService2();
        return "consumer dubbo invodel" + privadeResult;
    }
    @GetMapping(value = "/test2")
    public String test2(){
        return "consumer test2";
    }

    @GetMapping(value = "/service3")
    public String service3(){
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance serviceInstance = loadBalancerClient.choose("dubbo-service2");
        URI uri = serviceInstance.getUri();
        System.out.println("uri"+uri);
        //String result = restTemplate.getForObject("http://"+provider+"/service",String.class);
        String result = restTemplate.getForObject(uri+"/dubboService2",String.class);
        return "consumer invodel" + result;
    }
}
