package com.holmes.learn.designPattern.bridge;

public class Test {

    public static void main(String[] args) {

        Implementor implementor = (Implementor) XmlUtils.getBean("concrete");
        Abstraction abstraction = (Abstraction) XmlUtils.getBean("refined");
        abstraction.setImplementor(implementor);

        abstraction.operate();
    }
}
