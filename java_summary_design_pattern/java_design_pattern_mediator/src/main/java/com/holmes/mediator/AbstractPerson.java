package com.holmes.mediator;

/**
 * 抽象的Colleague角色
 */
public abstract class AbstractPerson {

    protected AbstractMediator mediator;

    protected String name;

    public AbstractPerson(String name, AbstractMediator mediator) {
        this.mediator = mediator;
        this.name = name;
    }

    public void setMediator(AbstractMediator mediator) {
        this.mediator = mediator;
    }

    protected abstract void sendMessage(String msg);

    protected abstract void getMessage(String msg);
}
