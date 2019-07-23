package com.holmes.springcloud.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "springcloud-provider")
public interface HelloService {

    @RequestMapping(value = "hello")
    public String hello(@RequestParam("name") String name);
}
