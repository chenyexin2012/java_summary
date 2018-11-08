package com.holmes.proxy;

/**
 * 目标类
 */
public class TargetService implements IService {

    @Override
    public void visitHolmes(User visitor) {
        System.out.println("I am glad to see you, " + visitor.getName() + ".");
    }
}
