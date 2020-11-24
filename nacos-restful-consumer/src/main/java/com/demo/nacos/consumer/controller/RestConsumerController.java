package com.demo.nacos.consumer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nacos.dubbo.service1.service.Service1Api;
import nacos.dubbo.service2.api.Service2Api;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


/**
 * @author qiaohui
 * @date 2020/9/23 16:44
 */
@Api(value = "RestController",tags =("RestController"))
@RestController
public class RestConsumerController {

    //通过负载均衡来发现地址(客户端的负载均衡器)
    @Autowired
    LoadBalancerClient loadBalancerClient;
//    @Value("${provider.address}")
//    private String provider;

    @Reference
    Service2Api service2Api;

    @Reference
    Service1Api service1Api;

    @GetMapping(value = "/service1")
    public String service1(){
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

    //service3调service1,service1调用service2
    @ApiOperation(value = "service3")
    @GetMapping(value = "/service3")
    public String service3(){
        String privadeResult = service1Api.dubboService1();
        return "consumer dubbo invodel" + privadeResult;
    }

    @Value("${common.name}")
    private String commom_name;


    @ApiOperation(value = "configs")
    @GetMapping(value = "/configs")
    public String getValue(){
        return commom_name;
    }

    @Autowired
    ConfigurableApplicationContext applicationContext;
    //该对象可以动态拿到配置，获取到实时最新配置

    @GetMapping(value = "/configs2")
    public String getValue2(){
        return applicationContext.getEnvironment().getProperty("common.name");
    }

    @GetMapping(value = "/configs3")
    public String getValue3(){
        String name = applicationContext.getEnvironment().getProperty("common.name");
        String addr = applicationContext.getEnvironment().getProperty("common.addr");
        return name +"||"+ addr;
    }



}
