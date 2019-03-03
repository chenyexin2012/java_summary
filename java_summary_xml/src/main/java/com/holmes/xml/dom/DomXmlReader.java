package com.holmes.xml.dom;

import com.holmes.xml.reflection.ReflectionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class DomXmlReader {

    private Map<String, Object> objectMap = new HashMap<>();

    public DomXmlReader() {

        // 获取解析器工厂
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        // 获取解析器
        try {
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document =
                    documentBuilder.parse(this.getClass().getClassLoader().getResourceAsStream("config.xml"));

            // 获取所有bean节点
            NodeList beanList = document.getElementsByTagName("bean");
            for (int i = 0; i < beanList.getLength(); i++) {
                Element node = (Element) beanList.item(i);
                NamedNodeMap map = node.getAttributes();
                String id = map.getNamedItem("id").getNodeValue();
                String classPackage = map.getNamedItem("class").getNodeValue();
                Object object = null;
                if (!"".equals(classPackage)) {
                    object = ReflectionUtils.newInstance(classPackage);
                    NodeList propertyList = node.getElementsByTagName("property");
                    for (int j = 0; j < propertyList.getLength(); j++) {
                        Element property = (Element) propertyList.item(j);
                        NamedNodeMap map2 = property.getAttributes();
                        String fieldName = map2.getNamedItem("name").getNodeValue();
                        String fieldValue = map2.getNamedItem("value").getNodeValue();
                        ReflectionUtils.setFieldValue(object, fieldName, fieldValue);
                    }
                }
                if (null != object) {
                    objectMap.put(id, object);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getBeanByName(String name) {
        return this.objectMap.get(name);
    }

    public static void main(String[] args) {
        DomXmlReader reader = new DomXmlReader();

        System.out.println(reader.getBeanByName("zhangsan"));
        System.out.println(reader.getBeanByName("lisi"));
    }
}
