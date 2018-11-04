package com.java.summary.proxy;

import com.java.summary.proxy.cglib.ProxyFactory;
import com.java.summary.proxy.jdk.MyInvocationHandler;
import net.sf.cglib.proxy.*;
import org.junit.Test;

import java.lang.reflect.Method;

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

    /**
     * @see net.sf.cglib.proxy.FixedValue
     */
    @Test
    public void testFieldValue() {

        // 被代理对象
        com.java.summary.proxy.cglib.Calculator calculator = new com.java.summary.proxy.cglib.Calculator();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(calculator.getClass());
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return 100;
            }
        });
        com.java.summary.proxy.cglib.Calculator proxy = (com.java.summary.proxy.cglib.Calculator) enhancer.create();

        System.out.println(proxy.add(1, 1));
        System.out.println(proxy.sub(1, 1));
        System.out.println(proxy.mul(1, 1));
        System.out.println(proxy.div(1, 1));
    }

    /**
     * @see net.sf.cglib.proxy.InvocationHandler
     */
    @Test
    public void testInvocationHandler() {

        // 被代理对象
        com.java.summary.proxy.cglib.Calculator calculator = new com.java.summary.proxy.cglib.Calculator();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(calculator.getClass());
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Integer result = (Integer) method.invoke(calculator, args);

                return result + 1;
            }
        });
        com.java.summary.proxy.cglib.Calculator proxy = (com.java.summary.proxy.cglib.Calculator) enhancer.create();

        System.out.println(proxy.add(1, 1));
        System.out.println(proxy.sub(1, 1));
        System.out.println(proxy.mul(1, 1));
        System.out.println(proxy.div(1, 1));
    }

    /**
     * @see net.sf.cglib.proxy.LazyLoader
     */
    @Test
    public void testLazyLoader() {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(com.java.summary.proxy.cglib.Calculator.class);
        enhancer.setCallback(new LazyLoader() {
            @Override
            public Object loadObject() throws Exception {
                System.out.println("通过回调生成对象");
                return new com.java.summary.proxy.cglib.Calculator();
            }
        });
        com.java.summary.proxy.cglib.Calculator proxy = (com.java.summary.proxy.cglib.Calculator) enhancer.create();
        System.out.println("在第一次访问对象时，加载对象");
        // 访问代理对象
        System.out.println(proxy);
    }

    /**
     * @see net.sf.cglib.proxy.Dispatcher
     */
    @Test
    public void testDispatcher() {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(com.java.summary.proxy.cglib.Calculator.class);
        enhancer.setCallback(new Dispatcher() {
            @Override
            public Object loadObject() throws Exception {
                System.out.println("通过回调生成对象");
                return new com.java.summary.proxy.cglib.Calculator();
            }
        });
        com.java.summary.proxy.cglib.Calculator proxy = (com.java.summary.proxy.cglib.Calculator) enhancer.create();
        System.out.println("第一次访问代理对象");
        // 访问代理对象
        System.out.println(proxy);
        System.out.println("第二次访问代理对象");
        // 访问代理对象
        System.out.println(proxy);
    }
}
