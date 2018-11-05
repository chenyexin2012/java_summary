package com.java.summary.rmi.client;

import com.java.summary.rmi.Calculator;
import com.java.summary.rmi.Input;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] args) {

        String url = "rmi://localhost:1999/calculator";
        try {
            // Naming.lookup(url) 寻找服务
            Calculator calculator = (Calculator) Naming.lookup(url);
            System.out.println(calculator.add(new Input(1, 2)));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
