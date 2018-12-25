package com.holmes.flyweight;

import org.junit.Test;

public class FlyweightTest {

    @Test
    public void flyweightTest() {

        VendingMachine vendingMachine = VendingMachine.getInstance();

        Commodity biscuits1 = vendingMachine.getCommodity(CommodityType.Biscuits);
        Commodity biscuits2 = vendingMachine.getCommodity(CommodityType.Biscuits);
        biscuits1.display();
        biscuits2.display();
        System.out.println(biscuits1 == biscuits2);

        Commodity crisps1 = vendingMachine.getCommodity(CommodityType.Crisps);
        Commodity crisps2 = vendingMachine.getCommodity(CommodityType.Crisps);
        crisps1.display();
        crisps2.display();
        System.out.println(crisps1 == crisps2);

        Commodity springWater1 = vendingMachine.getCommodity(CommodityType.SpringWater);
        Commodity springWater2 = vendingMachine.getCommodity(CommodityType.SpringWater);
        springWater1.display();
        springWater2.display();
        System.out.println(springWater1 == springWater2);
    }
}
