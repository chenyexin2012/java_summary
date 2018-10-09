package com.holmes.learn.designPattern.command;

class ConcreteCommand extends Command {
    private Receiver receiver; //维持一个对请求接收者对象的引用  

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    public void execute() {
        receiver.action(); //调用请求接收者的业务处理方法action()  
    }
}  