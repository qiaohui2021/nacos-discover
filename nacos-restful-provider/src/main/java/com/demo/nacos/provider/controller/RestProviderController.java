package com.demo.nacos.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiaohui
 * @date 2020/9/23 16:44
 */
@RestController
public class RestProviderController {

    @GetMapping(value = "/service")
    public String service(){
        System.out.println("privider invoke");
        return "privider invoke";
    }
}