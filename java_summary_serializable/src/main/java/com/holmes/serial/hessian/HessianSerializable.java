package com.holmes.serial.hessian;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HessianSerializable {

    public static void serializable(Object obj, String path) {

        Hessian2Output output = null;
        try {
            output = new Hessian2Output(new FileOutputStream(path));
            output.startMessage();
            output.writeObject(obj);
            output.completeMessage();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != output) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Object deSerializable(String path) {

        Hessian2Input input = null;
        Object obj = null;
        try {
            input = new Hessian2Input(new FileInputStream(path));
            input.startMessage();
            obj = input.readObject();
            input.completeMessage();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != input) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
}
