package com.holmes.interpreter;

public class NumberExpression implements Expression {

    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        return this.number;
    }

    @Override
    public String toString() {
        return String.valueOf(this.number);
    }
}
