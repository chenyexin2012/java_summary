package com.holmes.interpreter;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class Context {

    private List<String> operatorList;

    public Context(List<String> operatorList) {
        this.operatorList = Objects.requireNonNull(operatorList);
    }

    public int calculator() {

        Stack<Expression> stack = new Stack<>();

        for (String operator : this.operatorList) {

            Expression expression = null;
            if (isOperator(operator)) {

                Expression right = stack.pop();
                Expression left = stack.pop();

                expression = new NumberExpression(getOperatorInstance(operator, left, right).interpret());
            } else {
                expression = new NumberExpression(Integer.valueOf(operator));
            }
            stack.push(expression);
        }

        return stack.pop().interpret();
    }

    private static Expression getOperatorInstance(String operator, Expression left, Expression right) {

        switch (operator) {
            case "+":
                return new AddExpression(left, right);
            case "-":
                return new SubExpression(left, right);
            case "*":
                return new MulExpression(left, right);
            case "/":
                return new DivExpression(left, right);
            default:
                return null;
        }
    }

    private boolean isOperator(String operator) {

        if ("+".equals(operator) || "-".equals(operator) || "*".equals(operator) || "/".equals(operator)) {
            return true;
        }
        return false;
    }
}
