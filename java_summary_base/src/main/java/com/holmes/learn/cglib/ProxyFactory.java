package com.holmes.learn.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyFactory implements MethodInterceptor {

    private Object object;

    public Object creatProxy(Object target) {

        this.object = target;

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.object.getClass());
        enhancer.setCallback(this);
//		enhancer.setClassLoader(this.object.getClass().getClassLoader());

        return enhancer.create();
    }

    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy proxy) throws Throwable {

        Object result = null;
        try {
            // 前置通知  
//            before(method);  

            result = proxy.invokeSuper(obj, args);

            // 后置通知  
//            after(method);  
        } catch (Exception e) {
            exception();
        } finally {
            beforeReturning();
        }
        return result;
    }

    private void before(Method method) {
        System.out.println("before " + method.getName() + " invoke");
    }

    private void after(Method method) {
        System.out.println("after " + method.getName() + " invoke");
    }

    private void exception() {
        System.out.println("method invoke exception");
    }

    private void beforeReturning() {
        System.out.println("before returning");
    }
}
