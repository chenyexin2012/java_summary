package com.java.summary.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/helloCtr")
public class HelloController {

    @RequestMapping(value = "helloWorld")
    public String hello(Model model) {
        System.out.println("HelloController hello...");
        model.addAttribute("msg", "Hello Holmes!");
        return "hello";
    }

}
