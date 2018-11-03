package com.java.summary.proxy;

import com.java.summary.proxy.cglib.ProxyFactory;
import com.java.summary.proxy.jdk.MyInvocationHandler;
import org.junit.Test;

public class ProxyTest {

    /**
     * jdk动态代理测试
     */
    @Test
    public void testJdkProxy() {

        // 被代理对象
        com.java.summary.proxy.jdk.Calculator calculator = new com.java.summary.proxy.jdk.CalculatorImpl();

        // 代理对象
        com.java.summary.proxy.jdk.Calculator proxy = (com.java.summary.proxy.jdk.Calculator) new MyInvocationHandler(calculator).getProxyInstance();
        // 强转为实现类是会抛java.lang.ClassCastException异常
        // Calculator proxy = (CalculatorImpl) new MyInvocationHandler(calculator).getProxyInstance();

        System.out.println(proxy.add(1, 1));
        System.out.println(proxy.sub(1, 1));
        System.out.println(proxy.mul(1, 1));
        // 除数为0
        System.out.println(proxy.div(1, 0));
    }

    /**
     * cglib动态代理测试
     */
    @Test
    public void testCglibProxy() {

        // 被代理对象
        com.java.summary.proxy.cglib.Calculator calculator = new com.java.summary.proxy.cglib.Calculator();

        // 代理对象
        com.java.summary.proxy.cglib.Calculator proxy = (com.java.summary.proxy.cglib.Calculator) new ProxyFactory().getProxyInstance(calculator);

        System.out.println(proxy.add(1, 1));
        System.out.println(proxy.sub(1, 1));
        System.out.println(proxy.mul(1, 1));
        // 除数为0
        System.out.println(proxy.div(1, 0));
    }
}
