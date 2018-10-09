package com.holmes.learn.designPattern.flyweight;

public class ConcreteFlyweight implements Flyweight {

    private Character c;

    public ConcreteFlyweight(Character c) {
        this.c = c;
    }

    @Override
    public void operate() {
        System.out.println(ConcreteFlyweight.class + "=" + this.c);
    }

}
