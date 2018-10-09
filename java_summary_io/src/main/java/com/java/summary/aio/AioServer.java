package com.java.summary.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

public class AioServer {

    private final static int PORT = 6666;

    public AioServer() throws IOException {
        final AsynchronousServerSocketChannel serverSocketChannel =
                AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(PORT));
        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                // 接受下一个连接
                serverSocketChannel.accept(null, this);
                handler(result);
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("IO失败！");
            }
        });

        System.out.println("end ... ");
    }

    private void handler(AsynchronousSocketChannel socketChannel) {

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        try {
            int n = socketChannel.read(buffer).get();

            System.out.println(new String(buffer.array(), 0, n));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            new AioServer();
            Thread.sleep(100000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
