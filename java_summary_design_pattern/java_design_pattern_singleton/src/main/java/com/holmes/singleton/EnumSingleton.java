package com.holmes.singleton;

/**
 * 枚举类型的单例模式
 * 线程安全
 * 可以避免反射、序列化的问题
 */
public enum EnumSingleton {

    INSTANCE;

    public EnumSingleton getInstance(){
        return INSTANCE;
    }
}
