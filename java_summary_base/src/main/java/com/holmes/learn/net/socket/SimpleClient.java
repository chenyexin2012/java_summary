package com.holmes.learn.net.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author Administrator
 */
public class SimpleClient {

    private final static String MESSAGE = "hello server";

    public static void main(String[] args) {

        Socket socket = null;
        OutputStream os = null;
        InputStream is = null;
        try {
            socket = new Socket("127.0.0.1", 9999);
            System.out.println("successed to connect to server");
            System.out.println("send a message to server");
            //获取输出流，向服务端发送数据
            os = socket.getOutputStream();
            //传输字符，一般情况下必须指定字符编码
            os.write(MESSAGE.getBytes("UTF-8"));

            //此处需要关闭输出，否则服务端的读操作将一直循环下去
            socket.shutdownOutput();

            System.out.println("read a message from stream");
            is = socket.getInputStream();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bt = new byte[256];
            int n = -1;
            //当客户端关闭其输出流时，read会返回-1
            while ((n = is.read(bt)) != -1) {
                baos.write(bt, 0, n);
            }
            System.out.println("message:" + new String(baos.toByteArray(), "UTF-8"));

            //关闭输入
            socket.shutdownInput();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != socket) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("finished...");
        }
    }
}
