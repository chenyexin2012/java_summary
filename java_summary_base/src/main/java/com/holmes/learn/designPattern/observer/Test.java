package com.holmes.learn.designPattern.observer;

public class Test {

    public static void main(String[] args) {

        Subject subject = new ConcreteSubject();

        Observer observerA = new ConcreteObserverA();
        Observer observerB = new ConcreteObserverB();

        subject.add(observerA);
        subject.add(observerB);

        subject.remove(observerB);

        subject.notifyObservers();
    }
}
