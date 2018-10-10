package com.java.staticc;

public class ChildB extends Parent{

    private static ChildA childA = new ChildA();

    static {
        System.out.println("ChildB static block");
    }

    public ChildB() {
        System.out.println("ChildB constructor");
    }
}
