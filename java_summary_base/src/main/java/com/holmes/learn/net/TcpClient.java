package com.holmes.learn.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;

public class TcpClient {

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {

            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {

                    Socket socket = null;
                    InputStream is = null;
                    OutputStream os = null;
                    try {
                        socket = new Socket("127.0.0.1", 19090);
                        os = socket.getOutputStream();
                        os.write(String.valueOf(index % 3).getBytes());

                        socket.shutdownOutput();

                        is = socket.getInputStream();

                        byte[] b = new byte[1024];
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();

                        int n = -1;
                        while ((n = is.read(b)) != -1) {
                            baos.write(b, 0, n);
                        }
                        byte buf[] = baos.toByteArray();
                        String receive = new String(buf, 0, buf.length);

                        System.out.println(String.valueOf(count.incrementAndGet()) + "\t" + receive);

                        socket.shutdownInput();

                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (socket != null)
                                socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}