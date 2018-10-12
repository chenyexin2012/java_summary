package com.java.clazz.analysis.clazz;

/**
 * 类或是接口的访问标志信息
 */
public class ClassAccessFlags {

    public final static int ACC_PUBLIC = 0x0001;
    public final static int ACC_FINAL = 0x0010;
    /**
     * 是否允许使用invokespecial字节码指令的新语义，invokespecial指令的语义在jdk 1.0.2发生过改变，为了区别这条指令使用哪种
     * 语义，jdk 1.0.2之后编译出来的类的这个标志必须为真。
     */
    public final static int ACC_SUPER = 0x0020;
    public final static int ACC_INTERFACE = 0x0200;
    public final static int ACC_ABSTRACT = 0x0400;
    /**
     * 标识这个类并非由用户代码产生的
     */
    public final static int ACC_SYNTHETIC = 0x1000;
    public final static int ACC_ANNOTATION = 0x2000;
    public final static int ACC_ENUM = 0x4000;
}
