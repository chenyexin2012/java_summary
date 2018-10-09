package com.holmes.learn.designPattern.templateMethod;

public class ConcreteTemplateA extends Template {

    @Override
    protected void stepB() {
        System.out.println("ConcreteTemplateA stepB...");
    }

    @Override
    protected void stepD() {
        System.out.println("ConcreteTemplateA stepD...");
    }
}
