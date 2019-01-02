package com.holmes.visitor;

public class ChairmanVisitor implements Visitor {

    @Override
    public void visit(Chairman chairman) {
        System.out.println("visit " + chairman);
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
