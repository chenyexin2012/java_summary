package com.holmes.singleton;

/**
 * 非线程安全的单例模式
 * 只适合单线程环境下使用
 * 一般不使用
 */
public class NotThreadSafeSingleton {

    private static NotThreadSafeSingleton instance;

    private NotThreadSafeSingleton() {
        System.out.println("NotThreadSafeSingleton created...");
    }

    public static NotThreadSafeSingleton getInstance() {

        if(instance == null) {
            instance = new NotThreadSafeSingleton();
        }
        return instance;
    }
}
