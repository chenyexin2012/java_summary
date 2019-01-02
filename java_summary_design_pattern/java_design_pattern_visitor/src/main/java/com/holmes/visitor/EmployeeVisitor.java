package com.holmes.visitor;

public class EmployeeVisitor implements Visitor {

    @Override
    public void visit(Chairman chairman) {
        // do nothing
    }

    @Override
    public void visit(GeneralManager generalManager) {
        // do nothing
    }

    @Override
    public void visit(Employee employee) {
        System.out.println("visit " + employee);
    }
}
