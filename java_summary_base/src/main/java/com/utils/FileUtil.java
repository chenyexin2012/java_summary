package com.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FileUtil {

    public static void downLoadFile(String url, String name) {
        try {
            URL URL = new URL(url);
            URLConnection connection = URL.openConnection();
            connection.setConnectTimeout(3 * 1000);
            BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(name));
            byte[] buffer = new byte[128 * 1024];
            int n;
            while ((n = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, n);
            }
            bis.close();
            bos.close();
            System.out.println(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
