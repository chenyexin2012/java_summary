package com.holmes.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private DiscoveryClient client;

    @GetMapping(value = "hello")
    public String hello(String name) {

        ServiceInstance serviceInstance = client.getLocalServiceInstance();

        System.out.println("receive message from host " + serviceInstance.getHost() + ", service_id is " + serviceInstance.getServiceId() + ", name is " + name);

        return "Hello " + name;
    }
}
