package com.holmes.learn.net;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPFileDown {

    private static int port = 9090;

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;

        try {
            serverSocket = new ServerSocket(port);

            Socket socket = serverSocket.accept();

            dis = new DataInputStream(socket.getInputStream());

            System.out.println("开始接收文件。。。");
            String fileName = dis.readUTF();
            long length = dis.readLong();

            System.out.println("文件名为：" + fileName);

            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("G://" + fileName))));

            byte[] buf = new byte[102400];
            int n = -1;
            long l = 0;
            while ((n = dis.read(buf)) != -1) {
                dos.write(buf, 0, n);
                l += n;
                System.out.format("文件传输%.2f%%\n", l * 100.0 / length);
            }
            dos.flush();

            System.out.println("接收文件结束。。。");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != dis) {
                    dis.close();
                }
                if (null != dos) {
                    dos.close();
                }
                if (null != serverSocket) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
