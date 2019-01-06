package com.holmes.template;

public class Benz extends AbstractCar {

    public Benz(String name) {
        super(name);
    }

    @Override
    public void start() {
        System.out.println("脚踩刹车，点击启动按钮，" + this.name + "启动");
    }

    @Override
    public void stop() {
        System.out.println("点击启动按钮，" + this.name + "关闭");
    }
}
