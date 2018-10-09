package com.holmes.learn.designPattern.strategy;

public class Context {

    private Strategy strategy = null;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int operate(int a) {
        return strategy.operate(a);
    }
}
