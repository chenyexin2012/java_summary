package com.holmes.controller;

import com.holmes.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: hello world
 * @Author: holmes
 * @CreateDate: 2018/12/15 10:13
 * @Version: 1.0.0
 */
@RestController
public class HelloWorldController {

    @Autowired
    private Student student;
    /**
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        return "Hello " + student;
    }
}
