package com.holmes.visitor;

public interface Visitor {

    void visit(Chairman chairman);

    void visit(GeneralManager generalManager);

    void visit(Employee employee);
}
