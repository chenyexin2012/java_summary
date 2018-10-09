package com.holmes.learn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {

    public static void main(String[] args) {

        SocketChannel socketChannel = null;

        try {
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
            socketChannel.configureBlocking(false);

            ByteBuffer buffer = ByteBuffer.allocate(10240);
            buffer.put("Hello Server ".getBytes());
            buffer.flip();

            socketChannel.write(buffer);

            // 关闭输出
            socketChannel.shutdownOutput();

            buffer.clear();
            StringBuilder sb = new StringBuilder();
            int n = -1;
            while ((n = socketChannel.read(buffer)) != -1) {
                sb.append(new String(buffer.array(), 0, n));
                buffer.clear();
            }
            // 关闭输入
            socketChannel.shutdownInput();

            System.out.println("receive message = " + sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != socketChannel) {
                    socketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
