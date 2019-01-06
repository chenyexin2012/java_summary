package com.holmes.template;

public abstract class AbstractCar {

    protected String name;

    public AbstractCar(String name) {
        this.name = name;
    }

    public void working() {
        start();
        run();
        stop();
    }

    public void run() {
        System.out.println(this.name + "行驶中");
    }

    public abstract void start();

    public abstract void stop();
}
