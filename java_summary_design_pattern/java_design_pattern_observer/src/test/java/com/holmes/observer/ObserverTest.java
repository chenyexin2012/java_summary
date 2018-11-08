package com.holmes.observer;

import org.junit.Test;

public class ObserverTest {

    @Test
    public void test() {

        Subject subject = new ConcreteSubject();

        subject.addObserver(new ConcreteObserverA());
        subject.addObserver(new ConcreteObserverB());

        subject.change();
    }
}
