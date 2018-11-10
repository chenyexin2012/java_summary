package com.holmes.singleton;

/**
 * 使用同步方法的单利模式
 * 线程安全但是此种同步方式会降低性能
 * 无法避免反射、序列化的问题
 */
public class SynchronizedSingleton {

    private static SynchronizedSingleton instance;

    private SynchronizedSingleton() {
        System.out.println("SynchronizedSingleton created...");
    }

    public static synchronized SynchronizedSingleton getInstance() {

        if (instance == null) {
            instance = new SynchronizedSingleton();
        }
        return instance;
    }

}
