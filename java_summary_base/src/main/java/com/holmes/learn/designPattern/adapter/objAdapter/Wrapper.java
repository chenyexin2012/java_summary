package com.holmes.learn.designPattern.adapter.objAdapter;

import com.holmes.learn.designPattern.adapter.classAdapter.Source;
import com.holmes.learn.designPattern.adapter.classAdapter.Targetable;

public class Wrapper implements Targetable {

    private Source source;

    public Wrapper(Source source) {
        super();
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("this is method2");
    }
}
