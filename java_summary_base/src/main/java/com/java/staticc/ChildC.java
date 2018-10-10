package com.java.staticc;

public class ChildC extends Parent{

    private ChildA childA = new ChildA();

    static {
        System.out.println("ChildC static block");
    }

    public ChildC() {
        System.out.println("ChildC constructor");
    }
}
