package com.holmes.builder;

import org.junit.Test;

public class ComputerBuilderTest {

    @Test
    public void test() {

        Computer computer = new Computer.Builder()
                .addCpu(Cpu.CELERON)
                .addMemory(Memory.GAMER)
                .addGraphicsCard(GraphicsCard.NVIDIA)
                .addHardDisk(HardDisk.SEAGATE)
                .createComputer();

        System.out.println(computer);

    }
}
