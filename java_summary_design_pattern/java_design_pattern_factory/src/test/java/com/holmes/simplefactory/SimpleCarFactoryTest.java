package com.holmes.simplefactory;

import com.holmes.IMercedesBenz;
import org.junit.Test;

public class SimpleCarFactoryTest {

    @Test
    public void test() {

        SimpleCarFactory carFactory = new SimpleCarFactory();

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
