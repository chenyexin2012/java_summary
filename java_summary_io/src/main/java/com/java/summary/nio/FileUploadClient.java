package com.java.summary.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class FileUploadClient {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            // 模拟三个发端
            new Thread() {
                public void run() {
                    try {
                        SocketChannel socketChannel = SocketChannel.open();
                        socketChannel.socket().connect(new InetSocketAddress("127.0.0.1", 9090));
                        File file = new File("I://XXX");
                        FileChannel fileChannel = new FileInputStream(file).getChannel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
                        socketChannel.read(buffer);
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, buffer.limit(), Charset.forName("utf-8")));
                        buffer.clear();
                        int num = 0;
                        while ((num = fileChannel.read(buffer)) > 0) {
                            System.out.println(num);
                            buffer.flip();
                            socketChannel.write(buffer);
                            buffer.clear();
                        }
                        if (num == -1) {
                            fileChannel.close();
                            socketChannel.shutdownOutput();
                            System.out.println("输出关闭。。。");
                        }
                        // 接受服务器
                        socketChannel.read(buffer);
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, buffer.limit(), Charset.forName("utf-8")));
                        buffer.clear();
                        socketChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                ;
            }.start();

        }
        Thread.yield();
    }
}
