package com.java.summary.rw;

import org.junit.Test;

import java.io.*;

public class WriterDemo {

    private final static String str = "Don't stop, because others will more than you; Don't return, so as not to fall.";

    @Test
    public void WriterTest() {

        Writer writer = new Writer() {
            @Override
            public void write(char[] cbuf, int off, int len) throws IOException {

            }

            @Override
            public void flush() throws IOException {

            }

            @Override
            public void close() throws IOException {

            }
        };
    }

    /**
     *
     * @throws IOException
     * @see ReaderDemo#StringReaderTest()
     */
    @Test
    public void StringWriterTest() throws IOException {

        StringWriter sw = new StringWriter();
        sw.write(str);
        System.out.println(sw.getBuffer().toString());
        sw.close();
    }

    /**
     *
     * @throws IOException
     * @see ReaderDemo#FileReaderTest()
     */
    @Test
    public void FileWriterTest() throws IOException {

        FileWriter fw = new FileWriter("file/writer");
        fw.write(str);
        fw.close();
    }

    /**
     *
     * @throws IOException
     * @See ReaderDemo#CharArrayReaderTest()
     */
    @Test
    public void CharArrayWriterTest() throws IOException {

        CharArrayWriter caw = new CharArrayWriter();
        caw.write(str.toCharArray());
        System.out.println(caw.size());
        System.out.println(new String(caw.toCharArray()));
        System.out.println(caw.size());
        caw.writeTo(new FileWriter("file/writerArray"));
        //close为空方法
    }

    public void BufferedWriterTest() {

    }
}
