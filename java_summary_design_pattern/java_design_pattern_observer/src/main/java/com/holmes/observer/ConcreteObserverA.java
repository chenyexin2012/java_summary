package com.holmes.observer;

/**
 * 具体的观察者A
 */
public class ConcreteObserverA implements Observer{

    @Override
    public void update() {
        System.out.println("ConcreteObserverA update..");
    }
}
