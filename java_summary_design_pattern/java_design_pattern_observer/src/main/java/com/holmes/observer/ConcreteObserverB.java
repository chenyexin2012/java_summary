package com.holmes.observer;

/**
 * 具体的观察者B
 */
public class ConcreteObserverB implements Observer {

    @Override
    public void update() {
        System.out.println("ConcreteObserverB update...");
    }
}
