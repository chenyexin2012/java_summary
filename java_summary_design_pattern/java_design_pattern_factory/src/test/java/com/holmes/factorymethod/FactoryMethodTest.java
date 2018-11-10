package com.holmes.factorymethod;

import com.holmes.IMercedesBenz;
import org.junit.Test;

public class FactoryMethodTest {

    @Test
    public void test() {

        AbstractCarFactory carFactory = new BenzCarFactory();

        IMercedesBenz car = carFactory.createCar("BenzA");
        if (null != car) {
            car.show();
        }
        car = carFactory.createCar("BenzAMG");
        if (null != car) {
            car.show();
        }
        car = carFactory.createCar("BenzSLR");
        if (null != car) {
            car.show();
        }
        car = carFactory.createCar("BuickLaCross");
        if (null != car) {
            car.show();
        }
    }
}