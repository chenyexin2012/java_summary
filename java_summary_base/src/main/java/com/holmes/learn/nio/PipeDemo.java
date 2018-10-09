package com.holmes.learn.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.charset.Charset;
import java.util.Scanner;

public class PipeDemo {

    private Scanner scanner = null;

    private Pipe pipe = null;

    {
        try {
            scanner = new Scanner(new File("G://test"));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            pipe = Pipe.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class SinkThread implements Runnable {

        @Override
        public void run() {

            Pipe.SinkChannel sinkChannel = pipe.sink();
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            String msg = "";
            while (true) {
                buffer.clear();
                msg = scanner.nextLine();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                buffer.put(msg.getBytes());
                buffer.flip();
                try {
                    System.out.println(Thread.currentThread().getName()
                            + " send message:" + msg);
                    sinkChannel.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if ("stop".equals(msg))
                    break;
            }
            try {
                sinkChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println("sinkChannel closed...");
            }
        }
    }

    private class SourceThread implements Runnable {

        @Override
        public void run() {

            Pipe.SourceChannel sourceChannel = pipe.source();

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            String msg = "";
            while (true) {
                buffer.clear();
                try {
                    sourceChannel.read(buffer);
                    buffer.flip();
                    msg = new String(buffer.array(), 0, buffer.limit(), Charset.forName("UTF-8"));
                    System.out.println(Thread.currentThread().getName()
                            + " receive message:" + msg);
                    if ("stop".equals(msg))
                        break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                sourceChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println("sourceChannel closed...");
            }
        }
    }


    public static void main(String[] args) {

        PipeDemo pipeDemo = new PipeDemo();

        Thread sinkThread = new Thread(pipeDemo.new SinkThread());
        Thread sourceThread = new Thread(pipeDemo.new SourceThread());

        sinkThread.start();
        sourceThread.start();
    }
}
