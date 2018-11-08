package com.holmes.decorator;

/**
 * 一个具体的组件-->啥都没有没窗体
 */
public class Window implements Component {
    @Override
    public void display() {
        System.out.println("show window...");
    }
}
