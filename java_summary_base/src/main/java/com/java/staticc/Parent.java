package com.java.staticc;

public class Parent {

    protected int a = 1;

    static {
        System.out.println("Parent static block");
    }

    {
        System.out.println("Parent block");
    }

    public Parent() {
        System.out.println("Parent constructor");
    }
}
