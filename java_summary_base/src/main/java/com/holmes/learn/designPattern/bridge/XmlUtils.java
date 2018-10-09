package com.holmes.learn.designPattern.bridge;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlUtils {

    public static Object getBean(String org) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document document = dBuilder.parse(new File(XmlUtils.class.getClassLoader()
                    .getResource("config.xml").getPath()));

            NodeList nl = document.getElementsByTagName("className");

            Node node = null;

            switch (org) {
                case "concrete":
                    node = nl.item(0).getFirstChild();
                    break;
                case "refined":
                    node = nl.item(1).getFirstChild();
                    break;
                default:
                    break;
            }
            String className = node.getNodeValue();

            Class clazz = Class.forName(className);

            return clazz.newInstance();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
