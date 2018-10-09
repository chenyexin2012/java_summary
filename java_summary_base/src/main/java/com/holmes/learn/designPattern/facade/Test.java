package com.holmes.learn.designPattern.facade;

public class Test {

    public static void main(String[] args) {

        ComputerFacade computer = new ComputerFacade();

        computer.startup();
        computer.running();
        computer.shutdown();
    }
}
