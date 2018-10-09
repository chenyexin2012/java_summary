package com.holmes.learn.designPattern.observer;

public class ConcreteObserverB implements Observer {

    @Override
    public void update() {
        System.out.println(this.getClass() + " update...");
    }
}