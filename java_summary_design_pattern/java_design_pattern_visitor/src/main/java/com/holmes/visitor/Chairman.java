package com.holmes.visitor;

public class Chairman extends CompanyPosition {

    public Chairman(CompanyPosition... children) {
        super(children);
    }

    @Override
    public void accept(Visitor visitor) {
        super.accept(visitor);
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Chairman";
    }
}
