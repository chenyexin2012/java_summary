package com.java.summary.rmi.server;

import com.java.summary.rmi.Calculator;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

    public static void main(String[] args) {

        try {
            Calculator calculator = new CalculatorImpl();
            LocateRegistry.createRegistry(1999);
            Naming.bind("rmi://localhost:1999/calculator", calculator);
            System.out.println("rmi 服务启动成功。。。");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
