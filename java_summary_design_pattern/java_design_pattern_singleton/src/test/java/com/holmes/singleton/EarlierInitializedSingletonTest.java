package com.holmes.singleton;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EarlierInitializedSingletonTest {

    @Test
    public void test() {

        EarlierInitializedSingleton obj1 = EarlierInitializedSingleton.getInstance();
        EarlierInitializedSingleton obj2 = EarlierInitializedSingleton.getInstance();
        EarlierInitializedSingleton obj3 = null;

        // 利用反射调用构造函数，破坏单例模式
        Class clazz = EarlierInitializedSingleton.class;
        try {
            Constructor constructor = clazz.getDeclaredConstructor(null);
            constructor.setAccessible(true);
            obj3 = (EarlierInitializedSingleton) constructor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(obj1);
        System.out.println(obj2);
        System.out.println(obj3);
    }
}
