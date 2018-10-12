package com.java.clazz.analysis.attribute;

import com.java.clazz.analysis.utils.NumberUtil;

import java.util.Arrays;

/**
 * 属性结构
 *
 */
public class Attribute {

    /**
     * 属性的名称索引，在class文件中用两个字节表示
     */
    private int nameIndex;

    /**
     * 属性内容的字节长度
     */
    private int length;

    /**
     * 属性的内容
     */
    private byte[] info;

    public Attribute() {

    }

    public Attribute(byte[] buffer, int start) {

        // 两个字节的属性名称索引
        int attributeNameIndex = NumberUtil.byte2ToUnsignedShort(buffer, start);
        start += 2;
        this.nameIndex = attributeNameIndex;
        // 四个字节的属性内容的字节长度
        int attributeLength = NumberUtil.byte4ToInt(buffer, start);
        start += 4;
        this.length = attributeLength;

        byte[] info = Arrays.copyOfRange(buffer, start, start + attributeLength);
        this.info = info;
    }


    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getInfo() {
        return info;
    }

    public void setInfo(byte[] info) {
        this.info = info;
    }
}
