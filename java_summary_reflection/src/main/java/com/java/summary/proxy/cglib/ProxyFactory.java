package com.java.summary.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 使用CGLIB动态代理实现方式
 */
public class ProxyFactory implements MethodInterceptor {

    /**
     * 被代理对象
     */
    private Object object;

    public Object getProxyInstance(Object object) {

        this.object = object;
        // 动态代码生成器
        Enhancer enhancer = new Enhancer();
        // 设置回调
        enhancer.setCallback(this);
        // 设置生成类的父类类型
        enhancer.setSuperclass(this.object.getClass());
        // 设置类加载器
        enhancer.setClassLoader(this.object.getClass().getClassLoader());
        // 返回代理对象
        return enhancer.create();
    }

    /**
     * 执行代理对象的方法时会经过这个方法
     * @param obj
     * @param method
     * @param args
     * @param proxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        Object result = null;
        // 真正调用方法
        try {
            // 在代理对象在调用被代理对象的方法之前，可以自定义执行一些其它方法
            before(method);

            //
            result = proxy.invokeSuper(obj, args);

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
