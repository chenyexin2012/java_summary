package com.holmes.learn.designPattern.flyweight;

public class Test {

    public static void main(String[] args) {

        FlyweightFactory factory = FlyweightFactory.getFlyweightFactory();

        Flyweight flyweight = factory.getFlyweight('c');

        flyweight.operate();

        flyweight = factory.getFlyweight('~');

        flyweight.operate();
    }
}
