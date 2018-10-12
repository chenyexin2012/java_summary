package com.java.clazz.analysis.field;

import com.java.clazz.analysis.JavaClass;
import com.java.clazz.analysis.attribute.Attribute;
import com.java.clazz.analysis.field.FieldAccessFlags;
import com.java.clazz.analysis.utils.NumberUtil;

public class FieldAnalysis {

    private JavaClass javaClass = new JavaClass();

    public FieldAnalysis(JavaClass javaClass) {
        this.javaClass = javaClass;
    }

    public int analysis(byte[] buffer, int currentPos) {

        // 前两个字节表示字段表中的字段数量
        int fieldsCount = NumberUtil.byte2ToUnsignedShort(buffer, currentPos);
        currentPos += 2;
        System.out.println(">>>>>>>>>>>>>>>>>>>>字段表字段数量为：" + fieldsCount);

        while(fieldsCount -- > 0) {

            // 每一项的组成依次为：两个字节的访问标志、两个字节的简单名称索引，两个字节的描述符索引
            // 两个字节的属性表属性数量、各项属性记录

            // 访问标志 两个字节
            int fieldAccFlag = NumberUtil.byte2ToUnsignedShort(buffer, currentPos);
            currentPos += 2;
            System.out.println("访问标志为：");
            if ((fieldAccFlag & FieldAccessFlags.ACC_PUBLIC) > 0) {
                System.out.println("ACC_PUBLIC");
            }
            if ((fieldAccFlag & FieldAccessFlags.ACC_PRIVATE) > 0) {
                System.out.println("ACC_PRIVATE");
            }
            if ((fieldAccFlag & FieldAccessFlags.ACC_PROTECTED) > 0) {
                System.out.println("ACC_PROTECTED");
            }
            if ((fieldAccFlag & FieldAccessFlags.ACC_STATIC) > 0) {
                System.out.println("ACC_STATIC");
            }
            if ((fieldAccFlag & FieldAccessFlags.ACC_FINAL) > 0) {
                System.out.println("ACC_FINAL");
            }
            if ((fieldAccFlag & FieldAccessFlags.ACC_VOLATILE) > 0) {
                System.out.println("ACC_VOLATILE");
            }
            if ((fieldAccFlag & FieldAccessFlags.ACC_TRANSIENT) > 0) {
                System.out.println("ACC_TRANSIENT");
            }
            if ((fieldAccFlag & FieldAccessFlags.ACC_SYNTHETIC) > 0) {
                System.out.println("ACC_SYNTHETIC");
            }
            if ((fieldAccFlag & FieldAccessFlags.ACC_ENUM) > 0) {
                System.out.println("ACC_ENUM");
            }

            // 简单名称索引 两个字节
            int nameIndex = NumberUtil.byte2ToUnsignedShort(buffer, currentPos);
            currentPos += 2;
            System.out.println("简单名称索引：" + nameIndex);
            // 描述符索引 两个字节
            int descriptorIndex = NumberUtil.byte2ToUnsignedShort(buffer, currentPos);
            currentPos += 2;
            System.out.println("描述符索引：" + descriptorIndex);

            // 属性表中属性数量 两个字节
            int attributesCount = NumberUtil.byte2ToUnsignedShort(buffer, currentPos);
            currentPos += 2;

            System.out.println("属性表长度为：" + attributesCount);
            // 各项属性
            while(attributesCount -- > 0) {
                Attribute attribute = new Attribute(buffer, currentPos);
                currentPos += attribute.getLength() + 6;
            }

        }

        return currentPos;
    }
}
