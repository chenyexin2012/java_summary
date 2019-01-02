package com.holmes.visitor;

public class GeneralManagerVisitor implements Visitor {

    @Override
    public void visit(Chairman chairman) {
        // do nothing
    }

    @Override
    public void visit(GeneralManager generalManager) {
        System.out.println("visit " + generalManager);
    }

    @Override
    public void visit(Employee employee) {
        System.out.println("visit " + employee);
    }
}
