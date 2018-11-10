package com.holmes.abstractfactory;

import com.holmes.IAudi;
import com.holmes.IBuick;
import com.holmes.IMercedesBenz;

/**
 * 抽象的工厂类
 */
public interface AbstractCarFactory {

    public IAudi createAudi();

    public IMercedesBenz createBenz();

    public IBuick createBuick();
}
