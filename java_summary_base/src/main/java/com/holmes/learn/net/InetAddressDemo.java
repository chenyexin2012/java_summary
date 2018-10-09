package com.holmes.learn.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {

    public static void main(String[] args) throws UnknownHostException {

        InetAddress inetAddress = InetAddress.getByName("www.baidu.com");

        System.out.println(inetAddress);
        System.out.println(inetAddress.getHostAddress());
        System.out.println(inetAddress.getHostName());
    }
}
