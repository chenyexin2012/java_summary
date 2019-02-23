package com.holmes.summary.jvm.classload;

import java.io.*;

/**
 * 自定义类加载器
 *
 * @author Administrator
 */
public class MyClassLoader extends ClassLoader {

    private String classPath;

    public MyClassLoader() {
        this.classPath = this.getClass().getClassLoader().getResource("").getPath();
    }
    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {

        System.out.println("findClass...");
        byte[] data = getData(name);
        if (null == data) {
            throw new ClassNotFoundException();
        } else {
            // defineClass 的主要功能是将一个字节数组转换为class对象
            return defineClass(name, data, 0, data.length);
        }
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        System.out.println("loadClass " + name);
        // 调用父类的方法
        return super.loadClass(name, resolve);
    }

    private byte[] getData(String name) {

        System.out.println(name);
        String path = classPath + name.replace('.', File.separatorChar) + ".class";
        System.out.println(path);
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            is = new BufferedInputStream(new FileInputStream(path));
            baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];

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
