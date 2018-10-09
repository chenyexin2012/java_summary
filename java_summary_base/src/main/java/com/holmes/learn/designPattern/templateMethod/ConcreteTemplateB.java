package com.holmes.learn.designPattern.templateMethod;

public class ConcreteTemplateB extends Template {

    @Override
    protected void stepB() {
        System.out.println("ConcreteTemplateB stepB...");
    }

    @Override
    protected void stepD() {
        System.out.println("ConcreteTemplateB stepD...");
    }
}