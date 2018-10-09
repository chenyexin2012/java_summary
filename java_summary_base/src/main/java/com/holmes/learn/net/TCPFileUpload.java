package com.holmes.learn.net;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPFileUpload {

    private static int port = 9090;
    private static String host = "127.0.0.1";

    public static void main(String[] args) {

        Socket socket = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {
            socket = new Socket(host, port);

            File file = new File("I:/星球大战1：魅影危机.mkv");

            dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
            dos = new DataOutputStream(socket.getOutputStream());

            dos.writeUTF(file.getName());
            dos.flush();
            dos.writeLong(file.length());
            dos.flush();

            byte[] buf = new byte[102400];
            int n = -1;
            while ((n = dis.read(buf)) != -1) {
                dos.write(buf, 0, n);
            }
            dos.flush();

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
                if (null != socket) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
