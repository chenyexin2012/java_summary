package com.holmes.singleton;

import org.junit.Test;

public class SynchronizedSingletonTest {

    @Test
    public void test() {

        SynchronizedSingleton obj1 = null;
        SynchronizedSingleton obj2 = null;
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");

        obj1 = SynchronizedSingleton.getInstance();
        obj2 = SynchronizedSingleton.getInstance();

        System.out.println(obj1);
        System.out.println(obj2);
    }
}
