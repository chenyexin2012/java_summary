package com.holmes.learn.designPattern.proxy;

import com.holmes.learn.designPattern.decorator.Souceable;

public class Source implements Souceable {

    @Override
    public void method() {

        System.out.println("Source method...");
    }
}
