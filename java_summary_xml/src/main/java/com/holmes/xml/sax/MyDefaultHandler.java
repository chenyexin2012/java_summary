package com.holmes.xml.sax;

import com.holmes.xml.reflection.ReflectionUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class MyDefaultHandler extends DefaultHandler {

    private Map<String, Object> objectMap = new HashMap<>();

    private String currentName = "";

    private Object currentObj = null;

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("bean".equals(qName)) {
            currentName = attributes.getValue("id");
            String className = attributes.getValue("class");
            if (!"".equals(className)) {
                currentObj = ReflectionUtils.newInstance(className);
            }
        } else if (null != currentObj && "property".equals(qName)) {
            String fieldName = attributes.getValue("name");
            String fieldValue = attributes.getValue("value");
            ReflectionUtils.setFieldValue(currentObj, fieldName, fieldValue);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("bean".equals(qName)) {
            objectMap.put(currentName, currentObj);
            currentName = "";
            currentObj = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
    }

    public Object getBeanByName(String name) {
        return this.objectMap.get(name);
    }
}
