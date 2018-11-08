package com.holmes.singleton;

/**
 * 借助一个内部静态类实例化对象的单例模式
 * 线程安全
 * 无法避免反射、序列化的问题
 */
public class InitializingWithInnerStaticClassSingleton {

    private InitializingWithInnerStaticClassSingleton() {
        System.out.println("ThreadSafeInitializingWithInnerStaticClassSingleton created..");
    }

    public static InitializingWithInnerStaticClassSingleton getInstance() {
        return InnerStaticClass.INSTANCE;
    }

    private static class InnerStaticClass {
        private final static InitializingWithInnerStaticClassSingleton INSTANCE =
                new InitializingWithInnerStaticClassSingleton();
    }
}
