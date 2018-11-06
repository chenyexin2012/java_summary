package com.java.summary.rmi.jdk.client;

import com.java.summary.rmi.Input;
import com.java.summary.rmi.Output;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 当客户端使用的接口路径和服务端提供的不同，
 * 在Naming.lookup(url)寻找服务后无法进行强制类型转换
 */
public interface Calculator extends Remote {

    public Output add(Input input) throws RemoteException;

    public Output sub(Input input) throws RemoteException;

    public Output mul(Input input) throws RemoteException;

    public Output div(Input input) throws RemoteException;
}