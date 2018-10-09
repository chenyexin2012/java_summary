package com.java.summary.nio;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SelectorDemo {

    private static Map<SelectionKey, StringBuilder> channelMap = new HashMap<>();

    @org.junit.Test
    public void test() {

        ServerSocketChannel serverSocketChannel = null;
        Selector selector = null;
        Selector writeSelector = null;
        try {
            selector = Selector.open();
            writeSelector = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(19999));

            // 将通道设置为非阻塞，并注册至监听器
            serverSocketChannel.configureBlocking(false);
            SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            int interestSet = key.interestOps();

            System.out.println("isInterestedInAccept = "
                    + ((interestSet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT));
            System.out.println("isInterestedInConnect = "
                    + ((interestSet & SelectionKey.OP_CONNECT) == SelectionKey.OP_CONNECT));
            System.out.println("isInterestedInRead = "
                    + ((interestSet & SelectionKey.OP_READ) == SelectionKey.OP_READ));
            System.out.println("isInterestedInWrite = "
                    + ((interestSet & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE));

            while (true) {
                // 获取处于就绪状态的通道数
                int select = selector.selectNow();
                int writeSelect = writeSelector.selectNow();

                if (select <= 0 && writeSelect <= 0)
                    continue;

                Set<SelectionKey> keySet = selector.selectedKeys();

                Iterator<SelectionKey> iterator = keySet.iterator();

                System.out.println("select=" + select);
                System.out.println("keySet=" + keySet.size());

                while (iterator.hasNext()) {

                    SelectionKey selectionKey = iterator.next();

                    if (selectionKey.isAcceptable()) {

                        ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel channel = serverChannel.accept();

                        if (null == channel) {
                            continue;
                        }
                        System.out.println(channel);
                        channel.configureBlocking(false);
                        SelectionKey keyTmp = channel.register(selector, SelectionKey.OP_READ);

                        channelMap.put(keyTmp, new StringBuilder());
                    } else if (selectionKey.isReadable()) {

                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                        ByteBuffer buffer = ByteBuffer.allocate(1024);

                        StringBuilder sb = channelMap.get(selectionKey);

                        sb = new StringBuilder();

                        int n = -1;
                        while ((n = socketChannel.read(buffer)) > 0) {
                            sb.append(new String(buffer.array(), 0, n));
                            buffer.clear();
                        }

                        System.out.println(n);

                        System.out.println("message = " + sb.toString());

                        socketChannel.register(writeSelector, SelectionKey.OP_WRITE);

                        if (n < 0) {
                            selectionKey.cancel();
                            socketChannel.close();
                        }
                    }
                    iterator.remove();
                }

                keySet = writeSelector.selectedKeys();
                iterator = keySet.iterator();

                System.out.println("writeSelect=" + writeSelect);
                System.out.println("keySet=" + keySet.size());

                while (iterator.hasNext()) {

                    SelectionKey selectionKey = iterator.next();

                    if (selectionKey.isWritable()) {

                        ByteBuffer buffer = ByteBuffer.allocate(1024);

                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                        String httpResponse = "HTTP/1.1 200 OK\r\n" +
                                "Content-Length: 38\r\n" +
                                "Content-Type: text/html\r\n" +
                                "\r\n" +
                                "<html><body>Hello World!</body></html>";

                        byte[] httpResponseBytes = httpResponse.getBytes("UTF-8");
                        buffer.clear();
                        buffer.put(httpResponseBytes);
                        buffer.flip();

                        socketChannel.write(buffer);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @org.junit.Test
    public void client() {

        RandomAccessFile file = null;
        FileChannel fileChannel = null;
        SocketChannel channel = null;
        try {
            file = new RandomAccessFile("I://test.mkv", "rw");
            fileChannel = file.getChannel();

            channel = SocketChannel.open();
            channel.socket().connect(new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 19999));

            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);

            channel.read(buffer);
            buffer.flip();
            System.out.println(new String(buffer.array(), 0, buffer.limit()));
            buffer.clear();

            int count = -1;
            while ((count = fileChannel.read(buffer)) > 0) {

                buffer.flip();
                System.out.println(buffer.remaining());
                channel.write(buffer);
                buffer.clear();
            }

            if (count < 0) {
                fileChannel.close();
                channel.shutdownOutput();
                System.out.println("输出关闭。。。");
            }

            // 接受服务器
            channel.read(buffer);
            buffer.flip();
            System.out.println(buffer.remaining());
            System.out.println(new String(buffer.array(), 0, buffer.limit(), Charset.forName("utf-8")));
            buffer.clear();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (channel != null)
                    channel.close();
                if (file != null)
                    file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    Map<SelectionKey, FileChannel> fileMap = new HashMap<SelectionKey, FileChannel>();

    @Test
    public void server() {

        ServerSocketChannel serverSocketChannel = null;
        try {
            Selector selector = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(9090));

            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("服务开启。。。");

            while (true) {
                int select = selector.select();
                if (select <= 0)
                    continue;

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {

                    SelectionKey key = iterator.next();

                    if (key.isAcceptable()) {

                        ServerSocketChannel serverSocketChannelTmp = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = serverSocketChannelTmp.accept();

                        if (socketChannel == null)
                            continue;
                        socketChannel.configureBlocking(false);
                        SelectionKey keyTmp = socketChannel.register(selector, SelectionKey.OP_READ);
                        InetSocketAddress remoteAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
                        @SuppressWarnings("resource")
                        RandomAccessFile file = new RandomAccessFile(
                                "g://" + remoteAddress.getHostName() + "_" + remoteAddress.getPort() + ".mkv", "rw");
                        FileChannel fileChannel = file.getChannel();
                        fileMap.put(keyTmp, fileChannel);

                        String msg = socketChannel.getRemoteAddress() + "连接成功...";

                        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
                        buffer.put(msg.getBytes());
                        buffer.flip();
                        socketChannel.write(buffer);
                        buffer.clear();

                    } else if (key.isReadable()) {
                        read(key);
                    }
                    iterator.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("服务终止...");
            try {
                if (serverSocketChannel != null)
                    serverSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void read(SelectionKey key) {

        System.out.println("read buffer and write to file");

        FileChannel fileChannel = fileMap.get(key);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();

            int count = -1;
            while ((count = channel.read(byteBuffer)) > 0) {

                byteBuffer.flip();
                System.out.println(count);
//				currentLenth += byteBuffer.remaining();
//				System.out.format("当前传输%.2f%%\n", (currentLenth * 1.0 / length));
                fileChannel.write(byteBuffer);
                byteBuffer.clear();
            }
            System.out.println(count);

            if (count < 0) {
                fileChannel.close();
                key.cancel();

                System.out.println("文件接收完毕...");
                byteBuffer.clear();
                byteBuffer.put((channel.getRemoteAddress() + "上传成功...").getBytes());
                byteBuffer.flip();
                channel.write(byteBuffer);

                channel.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
