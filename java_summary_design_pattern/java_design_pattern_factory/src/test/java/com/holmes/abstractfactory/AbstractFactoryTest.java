package com.holmes.abstractfactory;

import org.junit.Test;

public class AbstractFactoryTest {

    @Test
    public void test() {

        AbstractCarFactory shanghaiCarFactory = new ShangHaiCarFactory();

        IAudi shanghaiAudi = shanghaiCarFactory.createAudi();
        if(null != shanghaiAudi) {
            shanghaiAudi.show();
        }
        IBuick shanghaiBuick = shanghaiCarFactory.createBuick();
        if(null != shanghaiBuick) {
            shanghaiBuick.show();
        }
        IMercedesBenz shanghaiBenz = shanghaiCarFactory.createBenz();
        if(null != shanghaiBenz) {
            shanghaiBenz.show();
        }

        AbstractCarFactory beijingCarFactory = new BeiJingCarFactory();
        IAudi beijingAudi = beijingCarFactory.createAudi();
        if(null != beijingAudi) {
            beijingAudi.show();
        }
        IBuick beijingBuick = beijingCarFactory.createBuick();
        if(null != beijingBuick) {
            beijingBuick.show();
        }
        IMercedesBenz beijingBenz = beijingCarFactory.createBenz();
        if(null != beijingBenz) {
            beijingBenz.show();
        }
    }
}
