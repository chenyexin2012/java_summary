package com.holmes.learn.designPattern.bridge;

public class RefinedAbstractionB extends Abstraction {

    @Override
    public void operate() {
        System.out.println("RefinedAbstractionB operate...");
        implementor.method();
    }

}
