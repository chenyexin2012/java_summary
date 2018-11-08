package com.holmes.singleton;

/**
 * 加双重校验锁的的单例模式
 * 线程安全
 * 无法避免反射、序列化的问题
 */
public class DoubleCheckLockingSingleton {

    private static DoubleCheckLockingSingleton instance;

    private DoubleCheckLockingSingleton() {
        System.out.println("ThreadSafeDoubleCheckLockingSingleton created...");
    }

    public static DoubleCheckLockingSingleton getInstance() {
        // 使用两层判断可防止指令重排序
        if (instance == null) {
            synchronized (DoubleCheckLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckLockingSingleton();
                }
            }
        }
        return instance;
    }
}
