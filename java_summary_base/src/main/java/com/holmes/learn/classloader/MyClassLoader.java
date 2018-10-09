package com.holmes.learn.classloader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

    private String classPath = this.getClass().getClassLoader().getResource("").getPath();

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {

        System.out.println("findClass...");
        byte[] data = getData(name);
        if (null == data) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, data, 0, data.length);
        }
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        System.out.println("loadClass " + name);
        return super.loadClass(name, resolve);
    }

    private byte[] getData(String name) {

        String path = classPath + name.replace('.', File.separatorChar) + ".class";
        System.out.println(path);
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            is = new BufferedInputStream(new FileInputStream(path));
            baos = new ByteArrayOutputStream();

            byte buffer[] = new byte[1024];

            int n = -1;
            while ((n = is.read(buffer)) != -1) {
                baos.write(buffer, 0, n);
            }
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
                if (null != baos) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
