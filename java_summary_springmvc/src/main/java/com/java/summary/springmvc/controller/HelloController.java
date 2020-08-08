package com.java.summary.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/helloCtr")
public class HelloController {

    @RequestMapping(value = "helloWorld")
    public String hello(Model model) {
        System.out.println("HelloController hello...");
        model.addAttribute("msg", "Hello Holmes!");
        return "hello";
    }

    @RequestMapping(value = "post", method = RequestMethod.POST)
    @ResponseBody
    public String post(@RequestBody String message) {
        System.out.println("HelloController post...");
        return "hello";
    }
}
