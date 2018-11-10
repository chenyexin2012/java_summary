package com.holmes;

/**
 * 奔驰A级 --> 具体的产品类
 */
public class BenzA implements IMercedesBenz {

    @Override
    public void show() {
        System.out.println("Benz A...");
    }
}
