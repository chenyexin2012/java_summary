package com.holmes.xml.pull;

import com.holmes.xml.reflection.ReflectionUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class PullXmlReader {

    private Map<String, Object> objectMap = new HashMap<>();

    public PullXmlReader() {

        try {
            XmlPullParserFactory pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser pullParser = pullParserFactory.newPullParser();

            pullParser.setInput(this.getClass().getClassLoader().getResourceAsStream("config.xml"), "UTF-8");

            Object object = null;
            String beanId = "";
            int type = pullParser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        if ("bean".equals(pullParser.getName())) {
                            beanId = pullParser.getAttributeValue(null, "id");
                            String className = pullParser.getAttributeValue(null, "class");
                            object = ReflectionUtils.newInstance(className);
                        } else if (null != object && "property".equals(pullParser.getName())) {
                            String fieldName = pullParser.getAttributeValue(null, "name");
                            String fieldValue = pullParser.getAttributeValue(null, "value");
                            ReflectionUtils.setFieldValue(object, fieldName, fieldValue);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (null != object && !"".equals(beanId) && "bean".equals(pullParser.getName())) {
                            objectMap.put(beanId, object);
                            beanId = "";
                            object = null;
                        }
                        break;
                    default:
                        break;
                }
                type = pullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getBeanByName(String name) {
        return this.objectMap.get(name);
    }

    public static void main(String[] args) {
        PullXmlReader reader = new PullXmlReader();

        System.out.println(reader.getBeanByName("zhangsan"));
        System.out.println(reader.getBeanByName("lisi"));
    }
}
