package com.holmes.learn.designPattern.strategy;

public class ConcreteStrategyC implements Strategy {

    @Override
    public int operate(int a) {
        return a * 3;
    }
}