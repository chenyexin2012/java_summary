package com.holmes.learn.designPattern.adapter.interfaceAdapter;

public class Test {

    public static void main(String[] args) {
        Wrapper wrapper1 = new Source1();

        wrapper1.method1();

        Wrapper wrapper2 = new Source2();

        wrapper2.method2();
    }
}
