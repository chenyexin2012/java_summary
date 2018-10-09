package com.holmes.learn.designPattern.adapter.objAdapter;

import com.holmes.learn.designPattern.adapter.classAdapter.Source;
import com.holmes.learn.designPattern.adapter.classAdapter.Targetable;

public class Test {

    public static void main(String[] args) {
        Source source = new Source();
        Targetable wrapper = new Wrapper(source);
        wrapper.method1();
        wrapper.method2();
    }
}
