package com.holmes.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置读取
 *
 * @author Administrator
 */
public class PropertyUtil {

    private static Properties props;

    static {
        loadProps();
    }

    synchronized static private void loadProps() {
        props = new Properties();
        InputStream in = PropertyUtil.class.getClassLoader().getResourceAsStream("config/application.properties");
        try {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }
}