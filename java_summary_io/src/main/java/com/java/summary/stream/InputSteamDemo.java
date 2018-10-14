package com.java.summary.stream;

import org.junit.Test;

import java.io.*;

/**
 * @see OutputStreamDemo
 */
public class InputSteamDemo {

    @Test
    public void InputStreamTest() {

        InputStream is = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
    }

    /**
     * @throws IOException
     * @see OutputStreamDemo#FileOutputStreamTest()
     */
    @Test
    public void FileInputStreamTest() throws IOException {

        StringBuffer sb = new StringBuffer();
        byte buffer[] = new byte[1024];
        FileInputStream fis = new FileInputStream("file/file");
        int n = 0;
        while ((n = fis.read(buffer)) >= 0) {
            sb.append(new String(buffer, 0, n));
        }
        System.out.println(sb.toString());
        fis.close();
    }

    /**
     * @throws IOException
     * @see OutputStreamDemo#ObjectOutputStreamTest()
     */
    @Test
    public void ObjectInputStreamTest() throws IOException, ClassNotFoundException {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("file/object"));
        System.out.println((MyObject) ois.readObject());
        System.out.println(ois.readInt());
        System.out.println(ois.readChar());

        ois.close();
    }

    /**
     * @see OutputStreamDemo#ByteArrayOutputStreamTest()
     */
    @Test
    public void ByteArrayInputStreamTest() {
    }

    /**
     * @throws IOException
     * @see OutputStreamDemo#BufferedOutputStreamTest()
     */
    @Test
    public void BufferedInputStreamTest() throws IOException {

        StringBuffer sb = new StringBuffer();
        byte buffer[] = new byte[1024];
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("file/buffer"));
        int n = 0;
        while ((n = bis.read(buffer)) >= 0) {
            sb.append(new String(buffer, 0, n));
        }
        System.out.println(sb.toString());

        bis.close();
    }

    /**
     * @throws IOException
     * @see OutputStreamDemo#DataOutputStreamTest()
     */
    @Test
    public void DataInputStreamTest() throws IOException {

        DataInputStream dis = new DataInputStream(new FileInputStream("file/data"));
        System.out.println(dis.readLong());
        System.out.println(dis.readDouble());
        System.out.println(dis.readUTF());
    }

    /**
     * @see OutputStreamDemo#PipedOutputStreamTest()
     */
    @Test
    public void PipedInputStreamTest() {
    }

}
