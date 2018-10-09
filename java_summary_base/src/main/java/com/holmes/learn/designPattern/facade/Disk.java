package com.holmes.learn.designPattern.facade;

public class Disk {

    public void startup() {
        System.out.println("disk start...");
    }

    public void running() {
        System.out.println("disk running...");
    }

    public void shutdown() {
        System.out.println("disk shutdown...");
    }
}
