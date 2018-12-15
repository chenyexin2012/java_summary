package com.holmes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: hello world
 * @Author: holmes
 * @CreateDate: 2018/12/15 10:13
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/spring/boot/")
public class HelloWorldController {

    /**
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Boot!";
    }
}
