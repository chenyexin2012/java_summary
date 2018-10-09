package com.holmes.learn.designPattern.bridge;

public class ConcreteImplementorB implements Implementor {

    @Override
    public void method() {
        System.out.println("ConcreteImplementorB method...");
    }

}
