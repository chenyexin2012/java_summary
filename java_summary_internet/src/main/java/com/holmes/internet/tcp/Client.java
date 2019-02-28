package com.holmes.internet.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Administrator
 */
public class Client {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("127.0.0.1", 9999));

            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();

            String message = in.next();

            os.write(message.getBytes());
            os.flush();
            socket.shutdownOutput();

            byte[] bytes = new byte[1024];
            int n = is.read(bytes);
            if (n != -1) {
                System.out.println(new String(bytes, 0, n));
            }
            socket.shutdownInput();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
