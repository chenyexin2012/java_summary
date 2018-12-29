package com.holmes.summary.jvm.dispatch;

/**
 * @Description: 静态分派
 * @Author: holmes
 * @Version: 1.0.0
 */
public class StaticDispatch {

    public static void main(String[] args) {

        Mozi mozi = new Mozi();

        // 静态类型为Horse，动态类型为BlackHorse
        Horse horse1 = new BlackHorse();
        // 静态类型为Horse，动态类型为WhiteHorse
        Horse horse2 = new WhiteHorse();

        mozi.ride(horse1);
        mozi.ride(horse2);
    }
}
