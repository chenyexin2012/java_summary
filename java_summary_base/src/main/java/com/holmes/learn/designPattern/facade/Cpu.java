package com.holmes.learn.designPattern.facade;

public class Cpu {

    public void startup() {
        System.out.println("cpu start...");
    }

    public void running() {
        System.out.println("cpu running...");
    }

    public void shutdown() {
        System.out.println("cpu shutdown...");
    }
}
