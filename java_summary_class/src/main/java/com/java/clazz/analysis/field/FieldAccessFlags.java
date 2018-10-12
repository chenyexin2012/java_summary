package com.java.clazz.analysis.field;

/**
 * 字段的访问标志
 */
public class FieldAccessFlags {

    public final static int ACC_PUBLIC = 0x0001;
    public final static int ACC_PRIVATE = 0x0002;
    public final static int ACC_PROTECTED = 0x0004;
    public final static int ACC_STATIC = 0x0008;
    public final static int ACC_FINAL = 0x0010;
    public final static int ACC_VOLATILE = 0x0040;
    public final static int ACC_TRANSIENT = 0x0080;
    /**
     * 字段是否由编译自动产生的
     */
    public final static int ACC_SYNTHETIC = 0x1000;
    public final static int ACC_ENUM = 0x4000;
}
