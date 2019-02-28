package com.holmes.internet.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author Administrator
 */
public class UdpServer {

    public static void main(String[] args) {

        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(9999);

            byte[] bytes = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(bytes, bytes.length);
            socket.receive(receivePacket);
            byte[] receive = receivePacket.getData();
            System.out.println(new String(receive));

            InetAddress address = receivePacket.getAddress();
            int port = receivePacket.getPort();
            bytes = "hello".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(bytes, bytes.length, address, port);
            socket.send(sendPacket);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
