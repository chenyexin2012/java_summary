package com.holmes.abstractfactory;

/**
 * 上海的汽车工厂 --> 工厂的具体实现类
 */
public class ShangHaiCarFactory implements AbstractCarFactory {

    @Override
    public IAudi createAudi() {
        return new AudiQ7();
    }

    @Override
    public IMercedesBenz createBenz() {
        return new BenzAMG();
    }

    @Override
    public IBuick createBuick() {
        return new BuickLaCrosse();
    }
}
