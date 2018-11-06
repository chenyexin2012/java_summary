package com.java.summary.rmi.jdk.server;

import com.java.summary.rmi.jdk.Calculator;
import com.java.summary.rmi.Input;
import com.java.summary.rmi.Output;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 继承自java.rmi.server.UnicastRemoteObject，表明这个类是远程方法调用的目标
 */
public class CalculatorImpl extends UnicastRemoteObject implements Calculator {

    /**
     * 构造方法需声明抛出RemoteException异常
     * @throws RemoteException
     */
    public CalculatorImpl() throws RemoteException {

    }

    @Override
    public Output add(Input input) throws RemoteException{
        System.out.println(input);
        return new Output(input.getA() + input.getB());
    }

    @Override
    public Output sub(Input input) throws RemoteException{
        System.out.println(input);
        return new Output(input.getA() - input.getB());
    }

    @Override
    public Output mul(Input input) throws RemoteException{
        System.out.println(input);
        return new Output(input.getA() * input.getB());
    }

    @Override
    public Output div(Input input) throws RemoteException{
        System.out.println(input);
        return new Output(input.getA() / input.getB());
    }
}
