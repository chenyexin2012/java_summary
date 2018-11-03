package com.java.summary.proxy.jdk;

/**
 * 被代理对象的类需要实现的接口
 */
public interface Calculator {

    public int add(int a, int b);

    public int sub(int a, int b);

    public int mul(int a, int b);

    public int div(int a, int b);
}
