package com.holmes.visitor;

public class Employee extends CompanyPosition {

    public Employee(CompanyPosition... children) {
        super(children);
    }

    @Override
    public void accept(Visitor visitor) {
        super.accept(visitor);
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Employee";
    }
}
