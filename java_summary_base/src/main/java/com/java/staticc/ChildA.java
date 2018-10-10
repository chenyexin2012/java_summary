package com.java.staticc;

public class ChildA extends Parent {

    protected int a = 2;

    static {
        System.out.println("ChildA static block");
    }

    {
        System.out.println("ChildA block");
    }

    public ChildA() {
        System.out.println("ChildA constructor");
    }
}


