package com.java.summary.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AioClient {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();

        Future<?> future = socketChannel.connect(new InetSocketAddress("127.0.0.1", 6666));

        System.out.println(future.get());

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("Hello World!".getBytes());
        buffer.flip();

        socketChannel.write(buffer);
    }
}
