package com.holmes.learn.designPattern.observer;

import java.util.LinkedList;

public class ConcreteSubject implements Subject {

    private LinkedList<Observer> observerList = new LinkedList<>();


    @Override
    public void add(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(Observer observer) {

        if (observerList.isEmpty()) return;

        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {

        for (Observer observer : observerList) {
            observer.update();
        }
    }

    @Override
    public void operation() {
        // TODO Auto-generated method stub

    }


}
