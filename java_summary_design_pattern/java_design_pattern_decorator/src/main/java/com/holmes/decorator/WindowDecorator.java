package com.holmes.decorator;

/**
 * 窗体的装饰者
 * 通过使用装饰者模式为对象增加功能
 */
public class WindowDecorator implements Component {

    private Component component;

    public WindowDecorator(Component component) {
        this.component = component;
    }

    @Override
    public void display() {
        System.out.println("show curtains...");
        component.display();
    }
}
