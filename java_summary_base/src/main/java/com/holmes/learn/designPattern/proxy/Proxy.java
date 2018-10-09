package com.holmes.learn.designPattern.proxy;

import com.holmes.learn.designPattern.decorator.Souceable;

public class Proxy implements Souceable {

    private Souceable souce;

    public Proxy(Souceable souce) {
        super();
        this.souce = souce;
    }

    @Override
    public void method() {
        before();
        souce.method();
        after();
    }

    private void after() {
        System.out.println("after method ...");
    }

    private void before() {
        System.out.println("before method ...");
    }
}
