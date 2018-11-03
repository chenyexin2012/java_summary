package com.java.summary.proxy;


import org.junit.Test;

public class ProxyTest {

    @Test
    public void test() {

        // 被代理对象
        Calculator calculator = new CalculatorImpl();

        // 代理对象
        Calculator proxy = (Calculator) new MyInvocationHandler(calculator).getProxyInstance();
        // 强转为实现类是会抛java.lang.ClassCastException异常
        // Calculator proxy = (CalculatorImpl) new MyInvocationHandler(calculator).getProxyInstance();

        System.out.println(proxy.add(1, 1));
        System.out.println(proxy.sub(1, 1));
        System.out.println(proxy.mul(1, 1));
        System.out.println(proxy.div(1, 1));
    }
}
