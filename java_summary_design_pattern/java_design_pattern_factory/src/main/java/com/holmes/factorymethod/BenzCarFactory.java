package com.holmes.factorymethod;

import com.holmes.BenzA;
import com.holmes.BenzAMG;
import com.holmes.BenzSLR;
import com.holmes.IMercedesBenz;

public class BenzCarFactory implements AbstractCarFactory {

    @Override
    public IMercedesBenz createCar(String type) {
        IMercedesBenz benz = null;
        if ("BenzA".equals(type)) {
            benz = new BenzA();
        } else if ("BenzAMG".equals(type)) {
            benz = new BenzAMG();
        } else if ("BenzSLR".equals(type)) {
            benz = new BenzSLR();
        } else {
            System.out.println("Error: Invalid type of car!");
        }
        return benz;
    }
}
