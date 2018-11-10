package com.holmes.abstractfactory;

import com.holmes.*;

/**
 * 北京的汽车工厂 --> 工厂的具体实现类
 */
public class BeiJingCarFactory implements AbstractCarFactory{
    @Override
    public IAudi createAudi() {
        return new AudiTT();
    }

    @Override
    public IMercedesBenz createBenz() {
        return new BenzSLR();
    }

    @Override
    public IBuick createBuick() {
        return new BuickLaCrosse();
    }
}
