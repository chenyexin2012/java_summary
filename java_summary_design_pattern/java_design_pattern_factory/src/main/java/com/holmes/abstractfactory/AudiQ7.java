package com.holmes.abstractfactory;

/**
 * 奥迪Q7 --> 具体的产品类
 */
public class AudiQ7 implements IAudi {
    @Override
    public void show() {
        System.out.println("Audi Q7...");
    }
}
