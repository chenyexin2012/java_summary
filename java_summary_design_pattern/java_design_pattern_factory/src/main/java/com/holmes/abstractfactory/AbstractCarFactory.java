package com.holmes.abstractfactory;

/**
 * 抽象的工厂类
 */
public interface AbstractCarFactory {

    public IAudi createAudi();

    public IMercedesBenz createBenz();

    public IBuick createBuick();
}
