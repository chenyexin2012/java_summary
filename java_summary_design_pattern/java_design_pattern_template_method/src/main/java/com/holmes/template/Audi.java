package com.holmes.template;

public class Audi extends AbstractCar {

    public Audi(String name) {
        super(name);
    }

    @Override
    public void start() {
        System.out.println("脚踩刹车并逆时针拧动钥匙，" + this.name + "启动");
    }

    @Override
    public void stop() {
        System.out.println("顺时针拧动钥匙，" + this.name + "关闭");
    }
}
