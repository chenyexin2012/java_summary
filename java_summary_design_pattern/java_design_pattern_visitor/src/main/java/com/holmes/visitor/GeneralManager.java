package com.holmes.visitor;

public class GeneralManager extends CompanyPosition {

    public GeneralManager(CompanyPosition... children) {
        super(children);
    }

    @Override
    public void accept(Visitor visitor) {
        super.accept(visitor);
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "GeneralManager";
    }
}
