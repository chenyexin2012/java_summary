package com.holmes.mediator;

/**
 * Colleague的一个具体实现
 */
public class Landlord extends AbstractPerson {

    public Landlord(String name, AbstractMediator mediator) {
        super(name, mediator);
    }

    @Override
    protected void sendMessage(String msg) {
        mediator.operation(this, msg);
    }

    @Override
    protected void getMessage(String msg) {
        System.out.println(name + "收到消息：" + msg);
    }
}
