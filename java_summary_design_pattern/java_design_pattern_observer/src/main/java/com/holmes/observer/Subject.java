package com.holmes.observer;

import java.util.LinkedList;
import java.util.List;

/**
 * 抽象的被观察者
 */
public abstract class Subject {

    List<Observer> observers;

    protected Subject() {
        observers = new LinkedList<Observer>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public abstract void change();
}
