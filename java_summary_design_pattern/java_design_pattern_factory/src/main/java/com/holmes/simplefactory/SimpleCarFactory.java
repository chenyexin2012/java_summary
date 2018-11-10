package com.holmes.simplefactory;

import com.holmes.BenzA;
import com.holmes.BenzAMG;
import com.holmes.BenzSLR;
import com.holmes.IMercedesBenz;

/**
 * 简单工厂模式
 */
public class SimpleCarFactory {

    public IMercedesBenz createCar(String type) {

        IMercedesBenz benz = null;
        if ("BenzA".equals(type)) {
            benz = new BenzA();
        } else if ("BenzAMG".equals(type)) {
            benz = new BenzAMG();
        } else if ("BenzSLR".equals(type)) {
            benz = new BenzSLR();
        }
        return benz;
    }
}
