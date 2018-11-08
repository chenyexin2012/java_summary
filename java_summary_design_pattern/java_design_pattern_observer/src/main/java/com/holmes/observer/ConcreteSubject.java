package com.holmes.observer;

/**
 * 具体的被观察者
 */
public class ConcreteSubject extends Subject {

    @Override
    public void change() {
        notifyAllObservers();
    }
}
