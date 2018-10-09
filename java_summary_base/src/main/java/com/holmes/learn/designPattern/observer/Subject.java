package com.holmes.learn.designPattern.observer;

public interface Subject {

    public void add(Observer observer);

    public void remove(Observer observer);

    public void notifyObservers();

    public abstract void operation();
}
