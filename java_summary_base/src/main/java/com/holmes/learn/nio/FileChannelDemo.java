package com.holmes.learn.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

public class FileChannelDemo {

    @org.junit.Test
    public void test() {

        RandomAccessFile fromFile = null;
        RandomAccessFile toFile = null;
        try {
            fromFile = new RandomAccessFile("I://test.mkv", "rw");
            toFile = new RandomAccessFile("G://to.mkv", "rw");

            FileChannel fromFileChannel = fromFile.getChannel();
            FileChannel toFileChannel = toFile.getChannel();

            System.out.println(fromFileChannel.size());
            long count = fromFileChannel.size();
            long position = 0;

            long startTime = System.currentTimeMillis();
//			fromFileChannel.transferTo(position, count, toFileChannel);
            toFileChannel.transferFrom(fromFileChannel, position, count);
            long endTime = System.currentTimeMillis();

            System.out.println((endTime - startTime) / 1000);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
