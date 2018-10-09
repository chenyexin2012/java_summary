package com.holmes.learn.designPattern.decorator;

public class Test {

    public static void main(String[] args) {
        Souceable source = new Souce();

        Souceable decorator = new Decorator(source);

        decorator.method();
    }
}
