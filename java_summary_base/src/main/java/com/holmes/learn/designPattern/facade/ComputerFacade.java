package com.holmes.learn.designPattern.facade;

public class ComputerFacade {

    private Cpu cpu;
    private Memory memory;
    private Disk disk;

    public ComputerFacade() {
        super();
        cpu = new Cpu();
        memory = new Memory();
        disk = new Disk();
    }

    public void startup() {

        System.out.println("computer start");
        cpu.startup();
        memory.startup();
        disk.startup();
        System.out.println("computer started");
    }

    public void running() {
        System.out.println("computer running");
        cpu.running();
        memory.running();
        disk.running();
        System.out.println("computer runned");
    }

    public void shutdown() {

        System.out.println("computer shutdown");
        cpu.shutdown();
        memory.shutdown();
        disk.shutdown();
        System.out.println("computer shutdowned");
    }
}
