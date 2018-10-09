package com.java.summary.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class NIOServer {

    private static Map<SelectionKey, StringBuilder> selectionMap = new HashMap<SelectionKey, StringBuilder>();

    public static void main(String[] args) {

        ServerSocketChannel serverSocketChannel = null;
        Selector selector = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(9999));
            // 将channel设置为非阻塞
            serverSocketChannel.configureBlocking(false);

            selector = Selector.open();
            // 将channel注册时选择器，并指定“感兴趣”的事件为accept
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {

                int select = selector.select();
                System.out.println("select = " + select);

                Set<SelectionKey> keySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keySet.iterator();

                while (iterator.hasNext()) {

                    SelectionKey selectionKey = iterator.next();

                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel = serverChannel.accept();
                        if (null == socketChannel) {
                            continue;
                        }
                        // 将获取的连接注册至选择器，并监听读事件
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);

                    } else if (selectionKey.isReadable()) {

                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                        StringBuilder sb = selectionMap.get(selectionKey);
                        if (null == sb) {
                            sb = new StringBuilder();
                            selectionMap.put(selectionKey, sb);
                        }
                        ByteBuffer buffer = ByteBuffer.allocate(10);
                        int n = -1;
                        while ((n = socketChannel.read(buffer)) > 0) {
                            System.out.println(n);
                            buffer.flip();
                            sb.append(new String(buffer.array(), 0, n));
                            buffer.clear();
                        }
                        System.out.println(n);
                        if (n == -1) {
                            selectionMap.remove(selectionKey);
                            System.out.println("receive message = " + sb.toString());
                            // 关闭输入
                            socketChannel.shutdownInput();
                            // 将感兴趣事件改为写
                            selectionKey.interestOps(SelectionKey.OP_WRITE);
                        }
                    } else if (selectionKey.isWritable()) {

                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        buffer.put("Hello Client".getBytes());
                        buffer.flip();
                        socketChannel.write(buffer);
                        // 关闭输出
                        socketChannel.shutdownOutput();
                        socketChannel.close();
                        selectionKey.channel();
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != serverSocketChannel) {
                    serverSocketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
