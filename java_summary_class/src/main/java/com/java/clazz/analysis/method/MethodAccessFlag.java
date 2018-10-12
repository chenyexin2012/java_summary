package com.java.clazz.analysis.method;

/**
 * 方法访问标志
 */
public class MethodAccessFlag {

    public final static int ACC_PUBLIC = 0x0001;
    public final static int ACC_PRIVATE = 0x0002;
    public final static int ACC_PROTECTED = 0x0004;
    public final static int ACC_STATIC = 0x0008;
    public final static int ACC_FINAL = 0x0010;
    public final static int ACC_SYNCHRONIZED= 0x0020;
    /**
     * 是否是由编译器产生的桥接方法
     */
    public final static int ACC_BRIDGE = 0x0040;
    /**
     * 方法是否接受不定参数
     */
    public final static int ACC_VARARGS = 0x0080;
    public final static int ACC_NATIVE = 0x0100;
    public final static int ACC_ABSTRACT = 0x0400;
    /**
     * 方法是否为strictpf
     */
    public final static int ACC_STRICTFP = 0x0800;
    /**
     * 方法是否是由编译器自动产生的
     */
    public final static int ACC_SYNTHETIC = 0x1000;
}
