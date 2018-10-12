package com.java.clazz.analysis.clazz;

import com.java.clazz.analysis.JavaClass;
import com.java.clazz.analysis.clazz.ClassAccessFlags;
import com.java.clazz.analysis.utils.NumberUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * 紧着这常量池之后的是用两个字节表示的类或接口的访问标志
 * 接下来为类索引、父类索引与接口索引
 * 类索引：两个字节
 * 父类索引：两个字节，这与类可以只能继承一个类一致，除了java.lang.Object类之外，其它类的父类索引都不会为0
 * 接口索引：一组数据的集合，每个数据都为两个字节，这与类可以实现多个接口一致
 *
 * 上述索引值指向CONSTANT_CLASS_INFO类型的常量，然后通过该常量可以找到CONSTANT_UTF8_INFO中的全限定名字符串
 */
public class ClassAnalysis {

    private JavaClass javaClass;


    public ClassAnalysis(JavaClass javaClass) {
        this.javaClass = javaClass;
    }

    public int analysis(byte[] buffer, int currentPos) {

        System.out.println("类或接口信息：");
        System.out.println("访问标志：");
        // 常量池结束后，接下来的就是类或是接口的访问标志 两个字节
        int classAccFlag = NumberUtil.byte2ToUnsignedShort(buffer, currentPos);
        currentPos += 2;

        if ((classAccFlag & ClassAccessFlags.ACC_PUBLIC) > 0) {
            System.out.println("ACC_PUBLIC");
        }
        if ((classAccFlag & ClassAccessFlags.ACC_FINAL) > 0) {
            System.out.println("ACC_FINAL");
        }
        if ((classAccFlag & ClassAccessFlags.ACC_SUPER) > 0) {
            System.out.println("ACC_SUPER");
        }
        if ((classAccFlag & ClassAccessFlags.ACC_INTERFACE) > 0) {
            System.out.println("ACC_INTERFACE");
        }
        if ((classAccFlag & ClassAccessFlags.ACC_ABSTRACT) > 0) {
            System.out.println("ACC_ABSTRACT");
        }
        if ((classAccFlag & ClassAccessFlags.ACC_SYNTHETIC) > 0) {
            System.out.println("ACC_SYNTHETIC");
        }
        if ((classAccFlag & ClassAccessFlags.ACC_ANNOTATION) > 0) {
            System.out.println("ACC_ANNOTATION");
        }
        if ((classAccFlag & ClassAccessFlags.ACC_ENUM) > 0) {
            System.out.println("ACC_ENUM");
        }

        int classIndex = NumberUtil.byte2ToUnsignedShort(buffer, currentPos);
        javaClass.setThisClassIndex(classIndex);
        currentPos += 2;
        System.out.println("类索引：" + classIndex);

        int parentClassIndex = NumberUtil.byte2ToUnsignedShort(buffer, currentPos);
        javaClass.setSuperClassIndex(parentClassIndex);
        currentPos += 2;
        System.out.println("父类索引：" + parentClassIndex);

        // 接下来为两个字节表示的接口索引数量
        int interfacesCount = NumberUtil.byte2ToUnsignedShort(buffer, currentPos);
        javaClass.setInterfacesCount(interfacesCount);
        System.out.println("接口数量为：" + interfacesCount);
        currentPos += 2;
        List<Integer> indexList = new LinkedList<Integer>();
        while(interfacesCount-- > 0) {
            // 每一项接口索引值
            int interfaceIndex = NumberUtil.byte2ToUnsignedShort(buffer, currentPos);
            indexList.add(interfaceIndex);

            currentPos += 2;
            System.out.println("接口索引：" + interfaceIndex);
        }
        javaClass.setInterfaceIndexList(indexList);

        return currentPos;
    }
}
