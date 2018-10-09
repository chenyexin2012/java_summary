package com.holmes.summary.jvm.chapter3;

/**
 * VM参数：-verbose:gc -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * -XX:PretenureSizeThreshold=3145728
 */
public class PretenureSizeThreshold {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {

        byte[] allocation;
        allocation = new byte[3 * _1MB];  //直接分配在老年代中
    }
}
