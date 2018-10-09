package com.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogUtil {

    private BlockingQueue<String> logQueue = null;

    private String filePath = null;

    private static volatile LogUtil logUtils = null;

    public static LogUtil getInstance() {

        if (logUtils == null) {
            synchronized (LogUtil.class) {
                if (logUtils == null) {
                    logUtils = new LogUtil();
                }
            }
        }
        return logUtils;
    }

    private LogUtil() {
        logQueue = new LinkedBlockingQueue<String>();
        filePath = "G://log.log";
        init();
    }

    public void log(String log) {
        try {
            logQueue.put(log);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        new Thread(new LogThread()).start();
    }

    private class LogThread implements Runnable {

        @Override
        public void run() {

            FileWriter writer = null;
            try {
                writer = new FileWriter(new File(filePath));
                String log = "";
                while (true) {
                    log = logQueue.take();
                    writer.write(log);
                    writer.write("\n");
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        LogUtil logUtil = LogUtil.getInstance();
    }
}
