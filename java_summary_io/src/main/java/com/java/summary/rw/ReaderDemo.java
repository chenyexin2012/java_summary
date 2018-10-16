package com.java.summary.rw;

import org.junit.Test;

import java.io.*;

public class ReaderDemo {

    private final static String str = "Person's life is to try, try, the more the more beautiful life.";
    @Test
    public void ReaderTest() {

        Reader reader = null;
    }

    /**
     *
     * @throws IOException
     * @see WriterDemo#StringWriterTest()
     */
    @Test
    public void StringReaderTest() throws IOException {

        char charBuffer[] = new char[1024];
        StringReader sr = new StringReader(str);
        int n = sr.read(charBuffer);
        System.out.println(new String(charBuffer, 0, n));
    }

    /**
     *
     * @throws IOException
     * @see WriterDemo#FileWriterTest()
     */
    @Test
    public void FileReaderTest() throws IOException {

        StringBuffer sb = new StringBuffer();
        char charBuffer[] = new char[1024];
        FileReader fr = new FileReader("file/writer");
        int n = -1;
        while((n = fr.read(charBuffer)) != -1) {
            sb.append(new String(charBuffer, 0, n));
        }
        System.out.println(sb.toString());
        fr.close();
    }

    /**
     *
     * @throws IOException
     * @see WriterDemo#CharArrayWriterTest()
     */
    @Test
    public void CharArrayReaderTest() throws IOException {

        StringBuffer sb = new StringBuffer();
        char charBuffer[] = new char[1024];
        CharArrayReader car = new CharArrayReader(str.toCharArray());
        int n = -1;
        while((n = car.read(charBuffer)) != -1) {
            sb.append(new String(charBuffer, 0, n));
        }
        System.out.println(sb.toString());
        car.close();
    }
    @Test
    public void BufferedReaderTest() {

//        BufferedReader br = new BufferedReader();
    }

    public void FilterReaderTest() {
        FilterReader fr = null;
    }

    public void InputStreamReaderTest() {
        InputStreamReader isr = null;
    }
}
