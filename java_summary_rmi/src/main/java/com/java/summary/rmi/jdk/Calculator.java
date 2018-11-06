package com.java.summary.rmi.jdk;

import com.java.summary.rmi.Input;
import com.java.summary.rmi.Output;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 1. 远程对象的接口必须扩展自java.rmi.Remote
 * 2. 必须声明抛出RemoteException异常
 * 3. 入参和返回值必须序列化
 */
public interface Calculator extends Remote {

    public Output add(Input input) throws RemoteException;

    public Output sub(Input input) throws RemoteException;

    public Output mul(Input input) throws RemoteException;

    public Output div(Input input) throws RemoteException;
}