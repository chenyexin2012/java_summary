package com.holmes.learn.designPattern.adapter.classAdapter;

public class Adapter extends Source implements Targetable {

    @Override
    public void method2() {
        System.out.println("this is method2");
    }
}
