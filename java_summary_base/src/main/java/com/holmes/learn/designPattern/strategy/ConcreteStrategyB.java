package com.holmes.learn.designPattern.strategy;

public class ConcreteStrategyB implements Strategy {

    @Override
    public int operate(int a) {
        return a * 2;
    }
}