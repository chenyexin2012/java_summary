package com.holmes.spring.aop;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-aop.xml")
public class AopTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void test() {

        Calculator calculator = context.getBean(Calculator.class);

        System.out.println(calculator.add(1, 1));
        System.out.println(calculator.sub(1, 1));
        System.out.println(calculator.mul(1, 1));
        System.out.println(calculator.div(1, 1));
    }
}
