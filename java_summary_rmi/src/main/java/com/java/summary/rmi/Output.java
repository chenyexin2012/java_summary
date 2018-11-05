package com.java.summary.rmi;

import java.io.Serializable;

public class Output implements Serializable {

    private int result;

    public Output(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Output{" +
                "result=" + result +
                '}';
    }
}
