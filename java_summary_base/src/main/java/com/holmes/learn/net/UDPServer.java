package com.holmes.learn.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    private static int port = 19090;

    public static void main(String[] args) throws IOException {

        byte[] data = "java是最好的开发语言".getBytes();

        DatagramSocket socket = new DatagramSocket(port);

        byte[] buf = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);

        socket.receive(receivePacket);

        InetAddress client = receivePacket.getAddress();
        byte[] bs = receivePacket.getData();
        int clientPort = receivePacket.getPort();
        System.out.println("客户端ip为：" + client.getHostAddress());
        System.out.println("客户端端口为：" + receivePacket.getPort());
        System.out.println("客户端发送数据为：" + new String(bs, 0, bs.length));


        DatagramPacket sendPacket = new DatagramPacket(data, data.length, client, clientPort);
        socket.send(sendPacket);

        socket.close();
    }
}
