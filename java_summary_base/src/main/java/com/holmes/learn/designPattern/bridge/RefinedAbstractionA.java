package com.holmes.learn.designPattern.bridge;

public class RefinedAbstractionA extends Abstraction {

    @Override
    public void operate() {
        System.out.println("RefinedAbstractionA operate...");
        implementor.method();
    }

}
