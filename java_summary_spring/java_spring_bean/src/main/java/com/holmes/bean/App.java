package com.holmes.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 */
public class App {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        ((ClassPathXmlApplicationContext) context).start();

        Student student = (Student) context.getBean("studentB");

        System.out.println(student);

        ((ClassPathXmlApplicationContext) context).close();

    }
}
