package com.java.clazz.analysis.constant;

import com.java.clazz.analysis.JavaClass;
import com.java.clazz.analysis.constant.ConstantInfo;
import com.java.clazz.analysis.utils.NumberUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstantPoolAnalysis {

    private JavaClass javaClass;
    /**
     * utf-8编码的字符串
     */
    private static final byte CONSTANT_UTF8_INFO = 1;
    private static final byte CONSTANT_INTEGER_INFO = 3;
    private static final byte CONSTANT_FLOAT_INFO = 4;
    private static final byte CONSTANT_LONG_INFO = 5;
    private static final byte CONSTANT_DOUBLE_INFO = 6;
    /**
     * 类或接口的符号引用
     */
    private static final byte CONSTANT_CLASS_INFO = 7;
    /**
     * 字符串类型字面量
     */
    private static final byte CONSTANT_STRING_INFO = 8;
    /**
     * 字段的符号引用
     */
    private static final byte CONSTANT_FIELD_REF_INFO = 9;
    /**
     * 类中方法的符号引用
     */
    private static final byte CONSTANT_METHOD_REF_INFO = 10;
    /**
     * 接口中方法的符号引用
     */
    private static final byte CONSTANT_INTERFACE_METHOD_REF_INFO = 11;
    /**
     * 字段或方法的部分符号引用
     */
    private static final byte CONSTANT_NAME_AND_TYPE_INFO = 12;
    /**
     * 表示方法句柄
     */
    private static final byte CONSTANT_METHOD_HANDLE_INFO = 15;
    /**
     * 标识方法类型
     */
    private static final byte CONSTANT_METHOD_TYPE_INFO = 16;
    /**
     * 表示一个动态方法的调用点
     */
    private static final byte CONSTANT_INVOKE_DYNAMIC_INFO = 18;

    /**
     * 记录所有的常量内容
     */
    private List<ConstantInfo> constantInfoList = null;

    public ConstantPoolAnalysis(JavaClass javaClass) {
        this.javaClass = javaClass;
    }

    public int analysis(byte[] buffer, int currentPos, int countOfConstant) {

        javaClass.setConstantPoolCount(countOfConstant);

        // 下标从1开始
        constantInfoList = new ArrayList<ConstantInfo>(countOfConstant + 1);
        constantInfoList.add(null);

        int count = 0;
        for (;;) {

            if (countOfConstant == 0) {
                break;
            }
            System.out.println("#" + ++count + "，常量类型：" + buffer[currentPos]);
            // 读取常量的项目类型
            // buffer[currentPos]读取的是每个常量项目的第一个字节，分别别是对应的类型
            switch (buffer[currentPos]) {
                case CONSTANT_UTF8_INFO:
                    currentPos = analysisUtf8Info(buffer, currentPos + 1);
                    countOfConstant --;
                    break;
                case CONSTANT_INTEGER_INFO:
                    currentPos = analysisIntegerInfo(buffer, currentPos + 1);
                    countOfConstant --;
                    break;
                case CONSTANT_FLOAT_INFO:
                    currentPos = analysisFloatInfo(buffer, currentPos + 1);
                    countOfConstant --;
                    break;
                case CONSTANT_LONG_INFO:
                    // Long记为两个常量，原因不明
                    currentPos = analysisLongInfo(buffer, currentPos + 1);
                    countOfConstant --;
                    countOfConstant --;
                    count ++;
                    break;
                case CONSTANT_DOUBLE_INFO:
                    // Double记为两个常量，原因不明
                    currentPos = analysisDoubleInfo(buffer, currentPos + 1);
                    countOfConstant --;
                    countOfConstant --;
                    count ++;
                    break;
                case CONSTANT_CLASS_INFO:
                    currentPos = analysisClassInfo(buffer, currentPos + 1);
                    countOfConstant --;
                    break;
                case CONSTANT_STRING_INFO:
                    currentPos = analysisStringInfo(buffer, currentPos + 1);
                    countOfConstant --;
                    break;
                case CONSTANT_FIELD_REF_INFO:
                    currentPos = analysisFieldRefInfo(buffer, currentPos + 1);
                    countOfConstant --;
                    break;
                case CONSTANT_METHOD_REF_INFO:
                    currentPos = analysisMethodRefInfo(buffer, currentPos + 1);
                    countOfConstant --;
                    break;
                case CONSTANT_INTERFACE_METHOD_REF_INFO:
                    currentPos = analysisInterfaceMethodRefInfo(buffer, currentPos + 1);
                    countOfConstant --;
                    break;
                case CONSTANT_NAME_AND_TYPE_INFO:
                    currentPos = analysisNameAndTypeInfo(buffer, currentPos + 1);
                    countOfConstant --;
                    break;
                case CONSTANT_METHOD_HANDLE_INFO:
                    currentPos = analysisMethodHandleInfo(buffer, currentPos + 1);
                    countOfConstant --;
                    break;
                case CONSTANT_METHOD_TYPE_INFO:
                    currentPos = analysisMethodTypeInfo(buffer, currentPos + 1);
                    countOfConstant --;
                    break;
                case CONSTANT_INVOKE_DYNAMIC_INFO:
                    currentPos = analysisInvokeDynamicInfo(buffer, currentPos + 1);
                    countOfConstant --;
                    break;
                default:
                    System.out.println("未知类型");
                    countOfConstant --;
                    break;
            }
        }

        System.out.println(constantInfoList.size());
        javaClass.setConstantPool(constantInfoList);
        return currentPos;
    }

    /**
     *  UTF-8编码的字符串
     *  包含三个部分：
     *      一个字节表示的类型tag
     *      两个字节表示的总字节数length（最大为65535）
     *      长度为length的表示字符串内容的字节数组bytes
     *  总占用字节数为：1 + 2 + length
     * @param buffer
     * @param currentPos
     * @return
     */
    private int analysisUtf8Info(byte[] buffer, int currentPos) {

        ConstantInfo constantInfo = new ConstantInfo();

        // 长度 两个字节
        constantInfo.setLength(NumberUtil.byte2ToUnsignedShort(buffer, currentPos));

        // 字符串内容 constantInfo.length个字节
        constantInfo.setBytes(Arrays.copyOfRange(buffer, currentPos + 2, currentPos + 2 + constantInfo.getLength()));

        try {
            System.out.println("UTF-8常量" + new String(constantInfo.getBytes(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        constantInfoList.add(constantInfo);
        return currentPos + 2 + constantInfo.getLength();
    }

    /**
     *  按照高位在前存储的int值
     *  包含两个部分：
     *      一个字节表示的类型tag
     *      四个字节字节数组bytes，表示高位在前存储的int值
     *  总占用字节数为：1 + 4
     * @param buffer
     * @param currentPos
     * @return
     */
    private int analysisIntegerInfo(byte[] buffer, int currentPos) {

        ConstantInfo constantInfo = new ConstantInfo();

        // Integer类型 四个字节 高位在前
        constantInfo.setTag(CONSTANT_INTEGER_INFO);
        constantInfo.setLength(4);
        constantInfo.setBytes(Arrays.copyOfRange(buffer, currentPos, currentPos + 4));

        System.out.println("整型常量：" + NumberUtil.byte4ToInt(constantInfo.getBytes(), 0));

        constantInfoList.add(constantInfo);
        return currentPos + 4;
    }

    /**
     *  按照高位在前存储的float值
     *  包含两个部分：
     *      一个字节表示的类型tag
     *      四个字节字节数组bytes，表示高位在前存储的float值
     *  总占用字节数为：1 + 4
     * @param buffer
     * @param currentPos
     * @return
     */
    private int analysisFloatInfo(byte[] buffer, int currentPos) {

        ConstantInfo constantInfo = new ConstantInfo();

        // Float类型 四个字节 高位在前
        constantInfo.setTag(CONSTANT_FLOAT_INFO);
        constantInfo.setLength(4);
        constantInfo.setBytes(Arrays.copyOfRange(buffer, currentPos, currentPos + 4));

        System.out.println("Float常量：" + NumberUtil.byte4ToFloat(constantInfo.getBytes(), 0));

        constantInfoList.add(constantInfo);
        return currentPos + 4;
    }

    /**
     *  按照高位在前存储的long值
     *  包含两个部分：
     *      一个字节表示的类型tag
     *      八个字节字节数组bytes，表示高位在前存储的long值
     *  总占用字节数为：1 + 8
     * @param buffer
     * @param currentPos
     * @return
     */
    private int analysisLongInfo(byte[] buffer, int currentPos) {

        ConstantInfo constantInfo = new ConstantInfo();

        // Long类型 八个字节 高位在前
        constantInfo.setTag(CONSTANT_LONG_INFO);
        constantInfo.setLength(8);
        constantInfo.setBytes(Arrays.copyOfRange(buffer, currentPos, currentPos + 8));

        System.out.println("Long常量：" + NumberUtil.byte8ToLong(constantInfo.getBytes(), 0));

        constantInfoList.add(constantInfo);
        // Long记为两个常量，原因不明，此处将后面一常量置为空，保持索引不变
        constantInfoList.add(null);
        return currentPos + 8;
    }

    /**
     *  按照高位在前存储的double值
     *  包含两个部分：
     *      一个字节表示的类型tag
     *      八个字节字节数组bytes，表示高位在前存储的double值
     *  总占用字节数为：1 + 8
     * @param buffer
     * @param currentPos
     * @return
     */
    private int analysisDoubleInfo(byte[] buffer, int currentPos) {

        ConstantInfo constantInfo = new ConstantInfo();

        // Double类型 八个字节 高位在前
        constantInfo.setTag(CONSTANT_DOUBLE_INFO);
        constantInfo.setLength(8);
        constantInfo.setBytes(Arrays.copyOfRange(buffer, currentPos, currentPos + 8));

        System.out.println("Double常量：" + NumberUtil.byte8ToDouble(constantInfo.getBytes(), 0));

        constantInfoList.add(constantInfo);
        // Double记为两个常量，原因不明，此处将后面一常量置为空，保持索引不变
        constantInfoList.add(null);
        return currentPos + 8;
    }

    /**
     *  类或接口的符号引用
     *  包含两个部分：
     *      一个字节表示的类型tag
     *      两个字节，指向全限定名常量的索引
     *  总占用字节数为：1 + 2
     * @param buffer
     * @param currentPos
     * @return
     */
    private int analysisClassInfo(byte[] buffer, int currentPos) {

        // 记录索引值 两个字节
        ConstantInfo constantInfo = new ConstantInfo();
        constantInfo.setTag(CONSTANT_CLASS_INFO);
        constantInfo.setIndex1(NumberUtil.byte2ToUnsignedShort(buffer, currentPos));

        System.out.println("全限定名常量索引值:" + constantInfo.getIndex1());
        constantInfoList.add(constantInfo);
        return currentPos + 2;
    }

    /**
     *  字符串字面量
     *  包含两个部分：
     *      一个字节表示的类型tag
     *      两个字节，指向字符串字面量的索引
     *  总占用字节数为：1 + 2
     * @param buffer
     * @param currentPos
     * @return
     */
    private int analysisStringInfo(byte[] buffer, int currentPos) {

        // String 记录索引值 两个字节
        ConstantInfo constantInfo = new ConstantInfo();
        constantInfo.setTag(CONSTANT_STRING_INFO);
        constantInfo.setIndex1(NumberUtil.byte2ToUnsignedShort(buffer, currentPos));

        System.out.println("String常量索引值:" + constantInfo.getIndex1());
        constantInfoList.add(constantInfo);
        return currentPos + 2;
    }

    /**
     *  字段的符号引用
     *  包含三个部分：
     *      一个字节表示的类型tag
     *      两个字节，指向申明字段的类或接口描述符Constant_class_info的索引值
     *      两个字节，指向申明字段描述符Constant_NameAndType的索引值
     *  总占用字节数为：1 + 2 + 2
     * @param buffer
     * @param currentPos
     * @return
     */
    private int analysisFieldRefInfo(byte[] buffer, int currentPos) {

        // FieldRef 记录两个索引值 均为两个字节
        ConstantInfo constantInfo = new ConstantInfo();
        constantInfo.setTag(CONSTANT_FIELD_REF_INFO);
        constantInfo.setIndex1(NumberUtil.byte2ToUnsignedShort(buffer, currentPos));
        System.out.println("CONSTANT_FIELDREF_INFO描述CONSTANT_CLASS_INFO常量索引值:" + constantInfo.getIndex1());

        constantInfo.setIndex2(NumberUtil.byte2ToUnsignedShort(buffer, currentPos + 2));
        System.out.println("CONSTANT_FIELDREF_INFO描述CONSTANT_NAMEANDTYPE_INFO常量索引值:" + constantInfo.getIndex2());

        constantInfoList.add(constantInfo);
        return currentPos + 4;
    }

    /**
     *  类中方法的符号引用
     *  包含三个部分：
     *      一个字节表示的类型tag
     *      两个字节，指向申明字段的类或接口描述符Constant_class_info的索引值
     *      两个字节，指向申明字段描述符Constant_NameAndType的索引值
     *  总占用字节数为：1 + 2 + 2
     * @param buffer
     * @param currentPos
     * @return
     */
    private int analysisMethodRefInfo(byte[] buffer, int currentPos) {

        // MethodRef 记录两个索引值 均为两个字节
        ConstantInfo constantInfo = new ConstantInfo();
        constantInfo.setTag(CONSTANT_METHOD_REF_INFO);
        constantInfo.setIndex1(NumberUtil.byte2ToUnsignedShort(buffer, currentPos));
        System.out.println("CONSTANT_METHODREF_INFO描述CONSTANT_CLASS_INFO常量索引值:" + constantInfo.getIndex1());

        constantInfo.setIndex2(NumberUtil.byte2ToUnsignedShort(buffer, currentPos + 2));
        System.out.println("CONSTANT_METHODREF_INFO描述CONSTANT_NAMEANDTYPE_INFO常量索引值:" + constantInfo.getIndex2());

        constantInfoList.add(constantInfo);
        return currentPos + 4;
    }

    /**
     *  接口中方法的符号引用
     *  包含三个部分：
     *      一个字节表示的类型tag
     *      两个字节，指向申明字段的类或接口描述符Constant_class_info的索引值
     *      两个字节，指向申明字段描述符Constant_NameAndType的索引值
     *  总占用字节数为：1 + 2 + 2
     * @param buffer
     * @param currentPos
     * @return
     */
    private int analysisInterfaceMethodRefInfo(byte[] buffer, int currentPos) {

        // InterfaceMethodRef 记录两个索引值 均为两个字节
        ConstantInfo constantInfo = new ConstantInfo();
        constantInfo.setTag(CONSTANT_INTERFACE_METHOD_REF_INFO);
        constantInfo.setIndex1(NumberUtil.byte2ToUnsignedShort(buffer, currentPos));
        System.out.println("CONSTANT_INTERFACEMETHODREF_INFO描述CONSTANT_CLASS_INFO常量索引值:" + constantInfo.getIndex1());

        constantInfo.setIndex2(NumberUtil.byte2ToUnsignedShort(buffer, currentPos + 2));
        System.out.println("CONSTANT_INTERFACEMETHODREF_INFO描述CONSTANT_NAMEANDTYPE_INFO常量索引值:" + constantInfo.getIndex2());

        constantInfoList.add(constantInfo);
        return currentPos + 4;
    }

    /**
     *  字段或方法的部分符号引用
     *  包含三个部分：
     *      一个字节表示的类型tag
     *      两个字节，指向该字段或方法名称常量项的索引值
     *      两个字节，指向该字段或方法描述符常量项的索引值
     *  总占用字节数为：1 + 2 + 2
     * @param buffer
     * @param currentPos
     * @return
     */
    private int analysisNameAndTypeInfo(byte[] buffer, int currentPos) {

        // NameAndType 记录两个索引值 均为两个字节
        ConstantInfo constantInfo = new ConstantInfo();
        constantInfo.setTag(CONSTANT_NAME_AND_TYPE_INFO);
        constantInfo.setIndex1(NumberUtil.byte2ToUnsignedShort(buffer, currentPos));
        System.out.println("该字段或方法名称常量索引值:" + constantInfo.getIndex1());

        constantInfo.setIndex2(NumberUtil.byte2ToUnsignedShort(buffer, currentPos + 2));
        System.out.println("该字段或方法描述符常量索引值:" + constantInfo.getIndex2());

        constantInfoList.add(constantInfo);
        return currentPos + 4;
    }

    /**
     *  方法句柄
     *  包含三个部分：
     *      一个字节表示的类型tag
     *      一个字节，reference_kind，值必须在1-9，包括1和9，它决定了方法句柄的类型，方法句柄类型的是表示方法句柄的字节码行为。
     *      两个字节，指向常量池的有效索引
     *  总占用字节数为：1 + 1 + 2
     * @param buffer
     * @param currentPos
     * @return
     */
    private int analysisMethodHandleInfo(byte[] buffer, int currentPos) {

        // NameAndType 记录句柄类型 一个字节 常量池有效索引 两个字节
        ConstantInfo constantInfo = new ConstantInfo();
        constantInfo.setTag(CONSTANT_METHOD_HANDLE_INFO);
        // reference_kind 值必须在1-9，包括1和9，它决定了方法句柄的类型，方法句柄类型的是表示方法句柄的字节码行为。
        constantInfo.setKind(buffer[currentPos]);
        System.out.println("方法句柄的类型:" + constantInfo.getKind());

        constantInfo.setIndex1(NumberUtil.byte2ToUnsignedShort(buffer, currentPos + 1));
        System.out.println("常量索引值:" + constantInfo.getIndex1());

        constantInfoList.add(constantInfo);
        return currentPos + 3;
    }

    /**
     *  方法类型
     *  包含两个部分：
     *      一个字节表示的类型tag
     *      两个字节，指向常量池CONSTANT_UTF8_INFO类型的有效索引，表示方法的描述符
     *  总占用字节数为：1 + 2
     * @param buffer
     * @param currentPos
     * @return
     */
    private int analysisMethodTypeInfo(byte[] buffer, int currentPos) {

        // MethodType 记录CONSTANT_UTF8_INFO常量索引 两个字节
        ConstantInfo constantInfo = new ConstantInfo();
        constantInfo.setTag(CONSTANT_METHOD_TYPE_INFO);

        constantInfo.setIndex1(NumberUtil.byte2ToUnsignedShort(buffer, currentPos));
        System.out.println("MethodType常量索引值:" + constantInfo.getIndex1());

        constantInfoList.add(constantInfo);
        return currentPos + 2;
    }


    /**
     *  方法类型
     *  包含三个部分：
     *      一个字节表示的类型tag
     *      两个字节，值必须是对当前Class文件中引导方法表示的bootstrap_methods[]数组的有效索引
     *      两个字节，指向常量池CONSTANT_NAME_AND_TYPE_INFO类型的有效索引，表示方法名和方法描述符
     *  总占用字节数为：1 + 2 + 2
     * @param buffer
     * @param currentPos
     * @return
     */
    private int analysisInvokeDynamicInfo(byte[] buffer, int currentPos) {

        // InvokeDynamic 记录两个索引值 均为两个字节
        ConstantInfo constantInfo = new ConstantInfo();
        constantInfo.setTag(CONSTANT_INVOKE_DYNAMIC_INFO);

        // 对当前Class文件中引导方法表bootstrap_methods[]数组的有效索引
        constantInfo.setIndex1(NumberUtil.byte2ToUnsignedShort(buffer, currentPos));
        System.out.println("bootstrap_methods[]数组的有效索引:" + constantInfo.getIndex1());

        // 对当前常量池的有效索引
        constantInfo.setIndex2(NumberUtil.byte2ToUnsignedShort(buffer, currentPos + 2));
        System.out.println("NameAndType常量索引值:" + constantInfo.getIndex2());

        constantInfoList.add(constantInfo);
        return currentPos + 4;
    }


}
