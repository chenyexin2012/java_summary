package com.holmes.learn.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import org.junit.Test;

public class DatagramChannelDemo {

    @Test
    public void test() {

        DatagramChannel datagramChannel = null;
        try {
            datagramChannel = DatagramChannel.open();
            datagramChannel.bind(new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 8888));

            String msg = "test message";

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(msg.getBytes());
            buffer.flip();

            datagramChannel.send(buffer, new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 9999));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (datagramChannel != null) {
                    datagramChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test2() {

        DatagramChannel datagramChannel = null;

        try {
            datagramChannel = DatagramChannel.open();
            datagramChannel.bind(new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 9999));

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            datagramChannel.receive(buffer);
            buffer.flip();

            System.out.println(new String(buffer.array(), 0, buffer.limit()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (datagramChannel != null) {
                    datagramChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
