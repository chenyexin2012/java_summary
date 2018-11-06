package com.java.summary.rmi.spring.server;

import com.java.summary.rmi.spring.Calculator;
import com.java.summary.rmi.Input;
import com.java.summary.rmi.Output;

public class CalculatorImpl implements Calculator {

    @Override
    public Output add(Input input){
        System.out.println(input);
        return new Output(input.getA() + input.getB());
    }

    @Override
    public Output sub(Input input){
        System.out.println(input);
        return new Output(input.getA() - input.getB());
    }

    @Override
    public Output mul(Input input){
        System.out.println(input);
        return new Output(input.getA() * input.getB());
    }

    @Override
    public Output div(Input input){
        System.out.println(input);
        return new Output(input.getA() / input.getB());
    }
}