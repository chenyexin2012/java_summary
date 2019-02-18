package com.holmes.serial.protostuff;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProtostuffSerialized {

    /**
     * Schema缓存
     */
    private static Map<Class<?>, Schema<?>> schemaCache = new ConcurrentHashMap<>();

    public static <T> void serializable(T obj, String path) {

        FileOutputStream fos = null;
        try {
            Class<T> clazz = (Class<T>) obj.getClass();
            LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
            Schema<T> schema = getSchema(clazz);
            byte[] bytes = ProtostuffIOUtil.toByteArray(obj, schema, buffer);

            fos = new FileOutputStream(path);
            fos.write(bytes);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static <T> T deSerializable(String path, Class<T> type) {

        T object = null;
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(path));
            byte[] bytes = new byte[bis.available()];
            bis.read(bytes, 0, bytes.length);

            Schema<T> schema = getSchema(type);
            object = type.newInstance();
            ProtostuffIOUtil.mergeFrom(bytes, object, schema);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return object;
    }

    private static <T> Schema<T> getSchema(Class<T> type) {

        Schema<T> schema = (Schema<T>) schemaCache.get(type);
        if (null == schema) {
            schema = RuntimeSchema.createFrom(type);
            if (null != schema) {
                schemaCache.put(type, schema);
            }
        }
        return schema;
    }
}
