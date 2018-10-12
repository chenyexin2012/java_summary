package com.java.clazz.analysis.method;

import com.java.clazz.analysis.JavaClass;
import com.java.clazz.analysis.attribute.Attribute;
import com.java.clazz.analysis.attribute.CodeAttribute;
import com.java.clazz.analysis.method.MethodAccessFlag;
import com.java.clazz.analysis.utils.NumberUtil;

import java.util.List;

public class MethodAnalysis {

    private JavaClass javaClass;

    public MethodAnalysis(JavaClass javaClass) {
        this.javaClass = javaClass;
    }

    public int analysis(byte[] buffer, int currentPos) {

        // 前两个字节表示字段表中的字段数量
        int fieldsCount = NumberUtil.byte2ToUnsignedShort(buffer, currentPos);
        currentPos += 2;
        System.out.println(">>>>>>>>>>>>>>>>>>>>方法表数量为：" + fieldsCount);

        while(fieldsCount -- > 0) {

            // 每一项的组成依次为：两个字节的访问标志、两个字节的简单名称索引，两个字节的描述符索引
            // 两个字节的属性表属性数量、各项属性记录

            // 访问标志 两个字节
            int methodAccFlag = NumberUtil.byte2ToUnsignedShort(buffer, currentPos);
            currentPos += 2;
            System.out.println("访问标志为：");
            if ((methodAccFlag & MethodAccessFlag.ACC_PUBLIC) > 0) {
                System.out.println("ACC_PUBLIC");
            }
            if ((methodAccFlag & MethodAccessFlag.ACC_PRIVATE) > 0) {
                System.out.println("ACC_PRIVATE");
            }
            if ((methodAccFlag & MethodAccessFlag.ACC_PROTECTED) > 0) {
                System.out.println("ACC_PROTECTED");
            }
            if ((methodAccFlag & MethodAccessFlag.ACC_STATIC) > 0) {
                System.out.println("ACC_STATIC");
            }
            if ((methodAccFlag & MethodAccessFlag.ACC_FINAL) > 0) {
                System.out.println("ACC_FINAL");
            }
            if ((methodAccFlag & MethodAccessFlag.ACC_SYNCHRONIZED) > 0) {
                System.out.println("ACC_SYNCHRONIZED");
            }
            if ((methodAccFlag & MethodAccessFlag.ACC_BRIDGE) > 0) {
                System.out.println("ACC_BRIDGE");
            }
            if ((methodAccFlag & MethodAccessFlag.ACC_VARARGS) > 0) {
                System.out.println("ACC_VARARGS");
            }
            if ((methodAccFlag & MethodAccessFlag.ACC_NATIVE) > 0) {
                System.out.println("ACC_NATIVE");
            }
            if ((methodAccFlag & MethodAccessFlag.ACC_ABSTRACT) > 0) {
                System.out.println("ACC_ABSTRACT");
            }
            if ((methodAccFlag & MethodAccessFlag.ACC_STRICTFP) > 0) {
                System.out.println("ACC_STRICTFP");
            }
            if ((methodAccFlag & MethodAccessFlag.ACC_SYNTHETIC) > 0) {
                System.out.println("ACC_SYNTHETIC");
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
                String attributeType = new String(javaClass.getConstantPool().get(attribute.getNameIndex()).getBytes());
                System.out.println("属性表名为：" + attributeType);
                if("Code".equals(attributeType)){
                    CodeAttribute codeAttribute = new CodeAttribute(attribute, javaClass);
                    System.out.println("<<<<<<<<<<<<<<<<<<<<<<<");
                    System.out.println(codeAttribute);
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
                }
                currentPos += attribute.getLength() + 6;
            }

        }

        return currentPos;
    }

    private class MethodInfo {

        private List<Attribute> attributeList;
    }
}
