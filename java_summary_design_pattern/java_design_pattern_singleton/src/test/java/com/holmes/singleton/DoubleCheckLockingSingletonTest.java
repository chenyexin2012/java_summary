package com.holmes.singleton;

import org.junit.Test;

public class DoubleCheckLockingSingletonTest {

    @Test
    public void test() {

        DoubleCheckLockingSingleton obj1 = null;
        DoubleCheckLockingSingleton obj2 = null;
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");

        obj1 = DoubleCheckLockingSingleton.getInstance();
        obj2 = DoubleCheckLockingSingleton.getInstance();

        System.out.println(obj1);
        System.out.println(obj2);
    }
}
