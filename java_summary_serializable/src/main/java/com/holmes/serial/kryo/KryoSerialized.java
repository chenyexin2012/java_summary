package com.holmes.serial.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class KryoSerialized {

    public static void serializable(Object obj, String path) {

        Kryo kryo = new Kryo();
        Output output = null;
        try {
            output = new Output(new FileOutputStream(path));
            kryo.writeObject(output, obj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(null != output) {
                output.close();
            }
        }
    }

    public static Object deSerializable(String path, Class type) {

        Object object = null;
        Kryo kryo = new Kryo();
        Input input = null;
        try {
            input = new Input(new FileInputStream(path));
            object = kryo.readObject(input, type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(null != input) {
                input.close();
            }
        }
        return object;
    }
}
