package com.holmes.learn.designPattern.proxy;

import com.holmes.learn.designPattern.decorator.Souceable;

public class Test {

    public static void main(String[] args) {

        Souceable souce = new Proxy(new Source());
        ;
        souce.method();
    }
}
