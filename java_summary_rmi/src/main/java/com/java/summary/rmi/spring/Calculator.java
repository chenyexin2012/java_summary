package com.java.summary.rmi.spring;

import com.java.summary.rmi.Input;
import com.java.summary.rmi.Output;

public interface Calculator {

    public Output add(Input input);

    public Output sub(Input input);

    public Output mul(Input input);

    public Output div(Input input);
}