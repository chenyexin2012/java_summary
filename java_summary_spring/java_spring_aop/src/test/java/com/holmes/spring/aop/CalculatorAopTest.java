package com.holmes.spring.aop;

import org.junit.Test;


public class CalculatorAopTest extends BaseTest {

    @Test
    public void test() {

        Calculator calculator = context.getBean(Calculator.class);

        System.out.println(calculator.add(1, 1));
        System.out.println(calculator.sub(1, 1));
        System.out.println(calculator.mul(1, 1));
        System.out.println(calculator.div(1, 0));
    }
}
