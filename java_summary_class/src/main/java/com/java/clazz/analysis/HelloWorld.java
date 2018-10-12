package com.java.clazz.analysis;

import java.io.Serializable;

public abstract class HelloWorld implements Serializable, HelloWorldInterface {

    private final static String message = "this is an order";

    private int a = 1;
    private float b = 0.23F;
    private double c = 236.366D;
    private long d = 316516516515L;
    private String str = "Hello World!";

    public HelloWorld() {

    }

    public void sayHello() {
        System.out.println(str);
    }

    public void saySth(String msg) {
        System.out.println(msg);
    }

    private int cal(int a, int b) {
        return a + b;
    }

    protected abstract String testAbstract();

    public static void main(String[] args) {

        HelloWorld helloWorld = new HelloWorld() {
            @Override
            protected String testAbstract() {
                return "";
            }
        };
        System.out.println(helloWorld.cal(1, 2));
        helloWorld.sayHello();
        helloWorld.saySth("Hello Java!");
    }
}
