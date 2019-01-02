package com.holmes.interpreter;

public class DivExpression implements Expression {

    private Expression left;

    private Expression right;

    public DivExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return this.left.interpret() / this.right.interpret();
    }

    @Override
    public String toString() {
        return "/";
    }
}
