package com.holmes.summary.jvm.dispatch;

/**
 * @Description: 动态分派
 * @Author: holmes
 * @Version: 1.0.0
*/
public class DynamicDispatch {

    public static void main(String[] args) {

        // 动态类型为Horse
        Horse horse = new Horse();
        horse.eat();

        // 动态类型为BlackHorse
        horse = new BlackHorse();
        horse.eat();

        // 动态类型为WhiteHorse
        horse = new WhiteHorse();
        horse.eat();
    }
}
