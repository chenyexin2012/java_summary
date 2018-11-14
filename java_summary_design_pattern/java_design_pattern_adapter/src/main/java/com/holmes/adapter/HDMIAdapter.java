package com.holmes.adapter;

/**
 * 类的适配器模式
 * 将HDMI接口适配至Vga接口
 */
public class HDMIAdapter extends DisplayWithVga implements HDMI{

    @Override
    public void showHDMI() {
        showVga();
    }
}
