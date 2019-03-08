package com.holmes.summary.jvm.classload;

public class ClassA {

    public final static int a = 0;

    static {
        System.out.println("ClassA static block");
    }
}
