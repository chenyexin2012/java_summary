package com.holmes.prototype;

import java.io.*;

/**
 * @Description:
 * @Author: holmes
 * @CreateDate: 2018/12/13 18:55
 * @Version: 1.0.0
 */
public class DeepCloneUser extends User implements DeepClone {

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}