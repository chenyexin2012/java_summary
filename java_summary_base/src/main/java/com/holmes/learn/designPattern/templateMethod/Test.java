package com.holmes.learn.designPattern.templateMethod;

public class Test {

    public static void main(String[] args) {

        Template templateA = new ConcreteTemplateA();
        templateA.template();

        System.out.println();

        Template templateB = new ConcreteTemplateB();
        templateB.template();
    }
}
