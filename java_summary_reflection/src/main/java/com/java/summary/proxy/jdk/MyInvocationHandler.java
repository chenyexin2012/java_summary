package com.java.summary.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 使用JDK提供的动态代理实现方式
 */
public class MyInvocationHandler implements InvocationHandler {

    /**
     * 需要被代理的真实对象
     */
    private Object obj;

    public MyInvocationHandler(Object obj) {
        this.obj = obj;
    }

    /**
     * 返回一个代理对象
     *
     * @return
     */
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), this.obj.getClass().getInterfaces(), this);
    }

    /**
     * 执行代理对象的方法时会经过这个方法
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;
        try {
            // 在代理对象在调用被代理对象的方法之前，可以自定义执行一些其它方法
            before(method);

            // 真正调用方法
            result = method.invoke(this.obj, args);

            // 在代理对象在调用被代理对象的方法之后，可以自定义执行一些其它方法
            after(method);

            System.out.println("result:" + result);
        } catch (Exception e) {
            // 异常时执行
            exception(method);
        } finally {
            // 返回之前执行
            beforeReturning(method);
        }

        // 需要返回执行结果，亦可以对返回值进行加工修改
        return result;
    }

    private void before(Method method) {
        System.out.println("before " + method.getName() + " execute...");
    }

    private void after(Method method) {
        System.out.println("after " + method.getName() + " execute...");
    }

    private void exception(Method method) {
        System.out.println(method.getName() + " invoke exception");
    }

    private void beforeReturning(Method method) {
        System.out.println("before " + method.getName() + " returning");
    }
}
