package com.holmes.singleton;

/**
 * 在类第一次被加载时初始化对象的单例模式
 * 线程安全
 * 无法避免反射、序列化的问题
 */
public class EarlierInitializedSingleton {

    private final static EarlierInitializedSingleton INSTANCE =
            new EarlierInitializedSingleton();

    private EarlierInitializedSingleton() {
        System.out.println("EarlierInitializedSingleton created...");
    }

    public static EarlierInitializedSingleton getInstance() {
        return EarlierInitializedSingleton.INSTANCE;
    }
}
