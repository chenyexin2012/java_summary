package com.holmes.learn.designPattern.strategy;

public class Test {

    public static void main(String[] args) {

        int a = 10;

        Context context = new Context(new ConcreteStrategyA());
        System.out.println(context.operate(a));

        context = new Context(new ConcreteStrategyB());
        System.out.println(context.operate(a));

        context = new Context(new ConcreteStrategyC());
        System.out.println(context.operate(a));
    }
}
