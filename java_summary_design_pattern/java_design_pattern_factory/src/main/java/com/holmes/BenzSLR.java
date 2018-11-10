package com.holmes;

/**
 * 奔驰SLR --> 具体的产品类
 */
public class BenzSLR implements IMercedesBenz {

    @Override
    public void show() {
        System.out.println("Benz SLR...");
    }
}
