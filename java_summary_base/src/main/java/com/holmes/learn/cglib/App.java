package com.holmes.learn.cglib;

import org.junit.Test;

/**
 * Hello world!
 */
public class App {

    @Test
    public void test() throws Exception {

        Hello hello = new Hello("test");

        ProxyFactory factory = new ProxyFactory();

        Hello proxy = (Hello) factory.creatProxy(hello);

        String result = proxy.sayHello(false);

        System.out.println("result：" + result);

        System.out.println(proxy.add(1, 2));
    }

    @Test
    public void test1() throws Exception {

        Calculator hello = new CalculatorImpl("Hello");

        MyInvocationHandler invocationHandler = new MyInvocationHandler(hello);

        //通过接口来实现对象的代理
        Calculator proxy = (Calculator) invocationHandler.getProxy();

        System.out.println(proxy.add(1, 1));
    }

    /**
     *
     */
    @Test
    public void test2() {

        Hello hello = new Hello("Hello");

        MyInvocationHandler invocationHandler = new MyInvocationHandler(hello);

        /**
         * jdk自带的动态代理技术具有限制，被代理的类只能实现自某个接口，因此程序运行到这里会
         * 抛出ClassCastException异常。
         */
        Hello proxy = (Hello) invocationHandler.getProxy();

        System.out.println(proxy.add(1, 1));
    }
}
