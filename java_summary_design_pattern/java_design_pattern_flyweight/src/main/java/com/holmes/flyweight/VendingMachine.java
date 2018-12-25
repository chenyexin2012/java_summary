package com.holmes.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: holmes
 * @Version: 1.0.0
 */
public class VendingMachine {

    private static VendingMachine vendingMachine = new VendingMachine();

    private Map<CommodityType, Commodity> commodityMap = null;

    private VendingMachine() {
        commodityMap = new HashMap<>(3);
        commodityMap.put(CommodityType.Biscuits, new Biscuits());
        commodityMap.put(CommodityType.Crisps, new Crisps());
        commodityMap.put(CommodityType.SpringWater, new SpringWater());
    }

    public static VendingMachine getInstance() {
        return vendingMachine;
    }

    public Commodity getCommodity(CommodityType commodityType) {
        return this.commodityMap.get(commodityType);
    }
}
