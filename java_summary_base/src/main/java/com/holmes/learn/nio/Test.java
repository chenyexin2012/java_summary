package com.holmes.learn.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Test {

    public static void main(String[] args) {

        /*
         * Java NIO has more classes and components than these, but the Channel, Buffer
         * and Selector forms the core of the API, in my opinion. The rest of the
         * components, like Pipe and FileLock are merely utility classes to be used in
         * conjunction with the three core components. Therefore, I'll focus on these
         * three components in this NIO overview. The other components are explained in
         * their own texts elsewhere in this tutorial. See the menu at the top corner of
         * this page.
         */
        Buffer buffer = null;

        /*
         * You can both read and write to a Channels. Streams are typically one-way
         * (read or write). Channels can be read and written asynchronously. Channels
         * always read to, or write from, a Buffer.
         */
        Channel channel = null;

        /*
         * A Selector allows a single thread to handle multiple Channel's.
         */
        Selector selector = null;


        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        SocketChannel channel = SocketChannel.open();

                        channel.socket().connect(new InetSocketAddress(
                                InetAddress.getByName("127.0.0.1"), 19999));

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        ByteBuffer buffer = ByteBuffer.allocate(1024);

                        buffer.put("ccccccc".getBytes());

                        buffer.flip();

                        channel.write(buffer);


                        channel.shutdownOutput();

                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        channel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
