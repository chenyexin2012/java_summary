package com.java.clazz.analysis;

import com.java.clazz.analysis.clazz.ClassAnalysis;
import com.java.clazz.analysis.constant.ConstantPoolAnalysis;
import com.java.clazz.analysis.field.FieldAnalysis;
import com.java.clazz.analysis.method.MethodAnalysis;
import com.java.clazz.analysis.utils.NumberUtil;

import java.io.IOException;
import java.io.InputStream;

public class Main {


    public static void main(String[] args) throws IOException {

        Main main = new Main();
        main.readClass();
    }

    public void readClass() throws IOException {

        JavaClass javaClass = new JavaClass();

        InputStream is = this.getClass().getResourceAsStream("HelloWorld.class");

        int length = is.available();

        byte[] buffer = new byte[length];

        is.read(buffer);

        // magic number 前四个字节
        System.out.println(Integer.toHexString(buffer[0]));
        System.out.println(Integer.toHexString(buffer[1]));
        System.out.println(Integer.toHexString(buffer[2]));
        System.out.println(Integer.toHexString(buffer[3]));

        // minor version 第五六个字节
        System.out.println(NumberUtil.byte2ToUnsignedShort(buffer, 4));
        // major version 第七八个字节
        System.out.println(NumberUtil.byte2ToUnsignedShort(buffer, 6));

        // count of constant pool 使用第九和第十个字节表示常量项的数目，因此得知最大的数量为65535个
        int countOfConstant = NumberUtil.byte2ToUnsignedShort(buffer, 8) - 1;
        System.out.println("常量数为：" + countOfConstant);

        // 紧接着为各常量项（每个常量项的第一个字节均表示其项目类型，共有14种）
        ConstantPoolAnalysis constantPoolAnalysis = new ConstantPoolAnalysis(javaClass);
        // 解析每个常量
        int curPos = constantPoolAnalysis.analysis(buffer, 10, countOfConstant);

        // 紧接着为两个字节表示的类或接口的访问标志，之后为两个字节的类索引、两个字节的父类索引
        // 两个字节的接口索引数量，之后表示每个接口的索引值，均为两个字节
        ClassAnalysis classAnalysis = new ClassAnalysis(javaClass);
        curPos = classAnalysis.analysis(buffer, curPos);

        // 紧接着为字段表集合，前两个字节表示数量
        FieldAnalysis fieldAnalysis = new FieldAnalysis(javaClass);
        curPos = fieldAnalysis.analysis(buffer, curPos);

        // 紧接着为方法表集合，前两个字节表示数量
        MethodAnalysis methodAnalysis = new MethodAnalysis(javaClass);
        curPos = methodAnalysis.analysis(buffer, curPos);

    }
}
