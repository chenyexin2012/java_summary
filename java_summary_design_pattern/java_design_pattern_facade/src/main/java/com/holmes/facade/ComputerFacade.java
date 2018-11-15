package com.holmes.facade;

public class ComputerFacade {

    private Cpu cpu;
    private Memory memory;
    private Disk disk;

    public ComputerFacade() {
        cpu = new Cpu();
        memory = new Memory();
        disk = new Disk();
    }

    public void startComputer() {
        cpu.startCpu();
        memory.startMemory();
        disk.startDisk();
    }

    public void shutdownComputer() {
        cpu.shutdownCpu();
        memory.shutdownMemory();
        disk.shutdownDisk();
    }
}
