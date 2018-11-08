package com.holmes.singleton;

import org.junit.Test;

public class InitializingWithInnerStaticClassSingletonTest {

    @Test
    public void test() {

        InitializingWithInnerStaticClassSingleton obj1 = null;
        InitializingWithInnerStaticClassSingleton obj2 = null;
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        obj1 = InitializingWithInnerStaticClassSingleton.getInstance();
        obj2 = InitializingWithInnerStaticClassSingleton.getInstance();

        System.out.println(obj1);
        System.out.println(obj2);
    }
}
