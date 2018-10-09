package com.holmes.learn.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

    private static String host = "127.0.0.1";
    private static int port = 19090;

    public static void main(String[] args) throws IOException {

        byte[] data = "Hello World!".getBytes();

        // 1.建立连接
        // 使用系统随机分配的端口，不需要指定服务端的ip和端口
        DatagramSocket datagramSocket = new DatagramSocket();
        // 也可指定端口创建链接，一般情况下无需指定端口
//		DatagramSocket datagramSocket = new DatagramSocket(9090);

        // 2.发送数据
        InetAddress server = InetAddress.getByName(host);
        DatagramPacket sendPacket = new DatagramPacket(data, data.length,
                server, port);
        datagramSocket.send(sendPacket);

        // 3.接收数据
        byte buf[] = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
        datagramSocket.receive(receivePacket);

        byte[] b = receivePacket.getData();
        InetAddress serverHost = receivePacket.getAddress();
        int serverPort = receivePacket.getPort();

        System.out.println("服务器ip：" + serverHost.getHostAddress());
        System.out.println("服务器端口为：" + serverPort);
        System.out.println("接收数据为：" + new String(b, 0, b.length));

        // 4.关闭连接
        datagramSocket.close();
    }
}
