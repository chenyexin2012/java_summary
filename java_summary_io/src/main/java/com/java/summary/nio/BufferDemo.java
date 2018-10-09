package com.java.summary.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

public class BufferDemo {

    @Test
    public void test() {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.clear();
        System.out.println("capacity=" + buffer.capacity());
        System.out.println("limit=" + buffer.limit());
        System.out.println("position=" + buffer.position());

        byte b[] = new byte[10];
        buffer.put(b);
        buffer.mark();
        buffer.put(b);
        buffer.reset();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("capacity=" + buffer.capacity());
        System.out.println("limit=" + buffer.limit());
        System.out.println("position=" + buffer.position());

        buffer.clear();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("capacity=" + buffer.capacity());
        System.out.println("limit=" + buffer.limit());
        System.out.println("position=" + buffer.position());
//		
//		buffer.flip();
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
//		System.out.println("capacity=" + buffer.capacity());
//		System.out.println("limit=" + buffer.limit());
//		System.out.println("position=" + buffer.position());
    }
}
