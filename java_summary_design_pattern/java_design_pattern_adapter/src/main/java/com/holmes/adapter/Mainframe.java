package com.holmes.adapter;

/**
 * 使用HDMI接口的主机，将通过适配器接入VGA接口的显示器
 */
public class Mainframe {

    private HDMI hdmi;

    public Mainframe(HDMI hdmi) {
        this.hdmi = hdmi;
    }

    public void show() {
        hdmi.showHDMI();
    }
}
