package com.java.summary.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 在代理对象在调用被代理对象的方法之前，可以自定义执行一些其它方法
        before(proxy, method, args);

        // 真正调用方法
        Object result = method.invoke(this.obj, args);
        System.out.println("result:" + result);

        // 在代理对象在调用被代理对象的方法之后，可以自定义执行一些其它方法
        after(proxy, method, args);

        // 需要返回执行结果，亦可以对返回值进行加工修改
        return result;
    }

    private void before(Object proxy, Method method, Object[] args) {
        System.out.println("before " + method.getName() + " execute...");
    }

    private void after(Object proxy, Method method, Object[] args) {
        System.out.println("after " + method.getName() + " execute...");
    }
}
