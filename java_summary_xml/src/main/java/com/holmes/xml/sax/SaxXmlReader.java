package com.holmes.xml.sax;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * @author Administrator
 */
public class SaxXmlReader {

    public SaxXmlReader() {

        try {
            MyDefaultHandler handler = new MyDefaultHandler();
            // 创建单例对象
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            saxParser.parse(this.getClass().getClassLoader().getResourceAsStream("config.xml"), handler);

            System.out.println(handler.getBeanByName("zhangsan"));
            System.out.println(handler.getBeanByName("lisi"));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SaxXmlReader();
    }

}
