package com.holmes.abstractfactory;

/**
 * 别克君越 --> 具体的产品类
 */
public class BuickLaCrosse implements IBuick {
    @Override
    public void show() {
        System.out.println("Buick LaCrosse...");
    }
}
