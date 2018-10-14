package com.java.summary.stream;

import org.junit.Test;

import java.io.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class OutputStreamDemo {

    private final static String str = "Where there's a will ,there's a way.With the boat burned,Qin's territory finall " +
            "belonged to Chu.The God won't cheat the hard working people.As the undergo self-imposed " +
            "hardships so as to strengthen his resolve,three thousand soldiers from Yue destroyed the " +
            "country of Wu.";

    @Test
    public void OutputStreamTest() {

        OutputStream os = new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        };
    }

    /**
     * @throws IOException
     * @see InputSteamDemo#FileInputStreamTest()
     */
    @Test
    public void FileOutputStreamTest() throws IOException {

        FileOutputStream fos = new FileOutputStream("file/file");
        fos.write(str.getBytes());
        fos.close();
    }

    /**
     * @throws IOException
     * @see InputSteamDemo#ObjectInputStreamTest()
     */
    @Test
    public void ObjectOutputStreamTest() throws IOException {

        MyObject object = new MyObject();
        object.setStrField(str);
        object.setLongField(12345L);
        object.setDoubleField(123.45D);
        object.setTransientFiled("transient str");

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("file/object"));
        // 写入对象信息
        oos.writeObject(object);
        // 写入java基本类型
        oos.writeInt(12345);
        oos.writeChar('c');

        oos.close();
    }

    /**
     * @throws IOException
     */
    @Test
    public void ByteArrayOutputStreamTest() throws IOException {

        byte buffer[] = new byte[1024];

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(str.getBytes());

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        int n = bais.read(buffer);
        System.out.println(new String(buffer, 0, n));
    }

    /**
     * @throws IOException
     * @see InputSteamDemo#BufferedInputStreamTest()
     */
    @Test
    public void BufferedOutputStreamTest() throws IOException {

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("file/buffer"));
        bos.write(str.getBytes());
//        bos.flush();
        bos.close();
    }

    /**
     * @throws IOException
     * @see InputSteamDemo#DataInputStreamTest()
     */
    @Test
    public void DataOutputStreamTest() throws IOException {

        DataOutputStream dis = new DataOutputStream(new FileOutputStream("file/data"));
        dis.writeLong(123456L);
        dis.writeDouble(123.45D);
        dis.writeUTF(str);

        dis.close();
    }

    /**
     * @throws IOException
     */
    @Test
    public void PipedOutputStreamTest() throws IOException {

        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = new PipedOutputStream();

        pis.connect(pos);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pos.write(str.getBytes());
                    pos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] buf = new byte[2048];
                try {
                    int len = -1;
                    while((len = pis.read(buf)) != -1) {
                        System.out.println(len);
                        System.out.println(new String(buf, 0, len));
                    }
                    pis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }
}
