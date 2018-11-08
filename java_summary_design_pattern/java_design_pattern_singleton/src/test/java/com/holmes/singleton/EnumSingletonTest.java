package com.holmes.singleton;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EnumSingletonTest {

    @Test
    public void test() {

        System.out.println(EnumSingleton.INSTANCE == EnumSingleton.INSTANCE);

        Class clazz = EnumSingleton.class;

        // 只有一个构造方法
        // private com.holmes.singleton.EnumSingleton(java.lang.String,int)
        // 调用构造方法创建对象
        // java.lang.IllegalArgumentException: Cannot reflectively create enum objects
        // java反射禁止调用枚举的构造函数
        Constructor constructors = null;
        try {
            constructors = clazz.getDeclaredConstructor(String.class, int.class);
            constructors.setAccessible(true);
            EnumSingleton obj = (EnumSingleton) constructors.newInstance("", 1);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
