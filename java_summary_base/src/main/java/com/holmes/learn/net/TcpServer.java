package com.holmes.learn.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.utils.LogUtil;

/**
 * @author Administrator
 */
public class TcpServer {

    private static LogUtil logger = LogUtil.getInstance();

    private static ThreadPoolExecutor executorService = null;

    private static BlockingQueue<Socket> socketQueue = new LinkedBlockingQueue<>(100);

    private static long countR = 0L;

    private static AtomicLong countH = new AtomicLong();

    public static void main(String[] args) throws IOException {

        executorService = new ThreadPoolExecutor(10,
                10, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(100), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread " + countH + " for handing request");
            }
        });


        @SuppressWarnings("resource")
        ServerSocket serverSocket = new ServerSocket(19090);

        System.out.println("服务启动....");

        TcpServer tcpServer = new TcpServer();

        executorService.execute(tcpServer.new ProcessThread());

        while (true) {

            Socket socket = serverSocket.accept();
            System.out.format("接收第%d个请求\n", countR++);
            try {
                socketQueue.put(socket);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class ProcessThread implements Runnable {

        @Override
        public void run() {

            while (true) {
                try {
                    final Socket socket = socketQueue.take();

                    executorService.execute(new Runnable() {

                        final Socket socketT = socket;
                        OutputStream os = null;
                        InputStream is = null;

                        @Override
                        public void run() {

                            long count = countH.incrementAndGet();

                            System.out.println(Thread.currentThread().getName() + "处理中第" + count + "个请求");
                            try {
                                is = socketT.getInputStream();

                                byte[] b = new byte[1024];
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                                int n = -1;
                                //只有当对方关闭了输出流，这里才会返回-1
                                while ((n = is.read(b)) != -1) {
                                    baos.write(b, 0, n);
                                }
                                byte buf[] = baos.toByteArray();
                                String receive = new String(buf, 0, buf.length);

                                logger.log("接收第 " + count + "个数据为：" + receive);

                                socketT.shutdownInput();

                                String send = "";
                                switch (receive) {
                                    case "1":
                                        send = "C++是最好的语言！";
                                        break;
                                    case "2":
                                        send = "java是最好的语言！";
                                        break;
                                    case "3":
                                        send = "PHP是最好的语言！";
                                        break;
                                    default:
                                        send = "接收到错误的数据！";
                                }

                                os = socketT.getOutputStream();

                                os.write(send.getBytes());

                                os.flush();

                                logger.log("返回第 " + count + "个数据为：" + send);
                                System.out.println(Thread.currentThread().getName() + "第" + count + "个请求处理结束");

                                socketT.shutdownOutput();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    if (null != socketT) {
                                        socketT.close();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
