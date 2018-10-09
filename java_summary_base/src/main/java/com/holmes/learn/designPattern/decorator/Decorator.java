package com.holmes.learn.designPattern.decorator;

public class Decorator implements Souceable {

    private Souceable souce;

    public Decorator(Souceable source) {
        super();
        this.souce = source;
    }

    @Override
    public void method() {
        System.out.println("before decorator...");
        souce.method();
        System.out.println("after decorator...");
    }
}
