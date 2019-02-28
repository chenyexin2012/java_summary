package com.holmes.internet.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Administrator
 */
public class Server {

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9999);
            while (true) {
                Socket socket = serverSocket.accept();

                InputStream is = socket.getInputStream();
                byte[] bytes = new byte[1024];
                int n = is.read(bytes);
                if (n != -1) {
                    System.out.println(new String(bytes, 0, n));
                }
                socket.shutdownInput();

                OutputStream os = socket.getOutputStream();
                os.write("hello client!".getBytes());
                os.flush();
                socket.shutdownOutput();

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != serverSocket) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
