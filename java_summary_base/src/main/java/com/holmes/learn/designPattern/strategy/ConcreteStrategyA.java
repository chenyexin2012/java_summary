package com.holmes.learn.designPattern.strategy;

public class ConcreteStrategyA implements Strategy {

    @Override
    public int operate(int a) {
        return a;
    }
}
