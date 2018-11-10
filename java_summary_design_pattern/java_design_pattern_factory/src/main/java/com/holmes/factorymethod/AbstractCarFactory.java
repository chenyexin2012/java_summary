package com.holmes.factorymethod;

import com.holmes.IMercedesBenz;

/**
 * 工厂方法模式
 * 一个抽象的工厂，由其子类决定创建何种对象
 */
public interface AbstractCarFactory {

    public IMercedesBenz createCar(String type);
}
