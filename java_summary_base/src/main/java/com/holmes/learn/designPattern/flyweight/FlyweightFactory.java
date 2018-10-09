package com.holmes.learn.designPattern.flyweight;

import java.util.HashMap;

public class FlyweightFactory {

    private static volatile FlyweightFactory flyweightFactory = null;

    private static HashMap<Character, Flyweight> hMap = new HashMap<>();

    public static FlyweightFactory getFlyweightFactory() {
        if (flyweightFactory == null) {
            synchronized (FlyweightFactory.class) {
                if (flyweightFactory == null) {
                    flyweightFactory = new FlyweightFactory();
                }
            }
        }
        return flyweightFactory;
    }

    private FlyweightFactory() {
        for (int i = 0; i < 127; i++) {
            hMap.put(Character.valueOf((char) i), new ConcreteFlyweight(Character.valueOf((char) i)));
        }
    }

    public Flyweight getFlyweight(Character c) {

        if (hMap.containsKey(c)) {
            return hMap.get(c);
        }
        return null;
    }
}
