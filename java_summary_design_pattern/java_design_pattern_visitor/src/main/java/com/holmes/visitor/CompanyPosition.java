package com.holmes.visitor;

public abstract class CompanyPosition {

    private CompanyPosition[] children;

    public CompanyPosition(CompanyPosition... children) {
        this.children = children;
    }

    /**
     * Accept visitor
     */
    public void accept(Visitor visitor) {
        for (CompanyPosition child : children) {
            child.accept(visitor);
        }
    }
}