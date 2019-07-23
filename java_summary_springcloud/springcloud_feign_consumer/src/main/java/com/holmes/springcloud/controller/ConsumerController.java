package com.holmes.springcloud.controller;

import com.holmes.springcloud.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private HelloService helloService;

    @GetMapping(value = "hello")
    public String hello(String name) {

        return helloService.hello(name);
    }
}