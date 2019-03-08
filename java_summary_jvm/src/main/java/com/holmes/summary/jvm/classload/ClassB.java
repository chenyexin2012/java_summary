package com.holmes.summary.jvm.classload;

public class ClassB {

    public static int a = 0;

    static {
        System.out.println("ClassB static block");
        a = 1;
    }
}
