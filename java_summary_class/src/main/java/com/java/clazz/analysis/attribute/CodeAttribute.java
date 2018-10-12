package com.java.clazz.analysis.attribute;

import com.java.clazz.analysis.JavaClass;
import com.java.clazz.analysis.utils.NumberUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeAttribute {

    private JavaClass javaClass;

    /**
     * 属性的名称索引，在class文件中用两个字节表示
     */
    private int nameIndex;

    /**
     * 属性内容的字节长度 4个字节
     */
    private int length;

    /**
     * 操作数栈深度的最大值 2个字节
     */
    private int maxStack;

    /**
     * 局部变量表所需的存储空间，单位为Slot（虚拟机为局部变量分配内存所使用的最小单位）
     * 2个字节
     */
    private int maxLocals;

    /**
     * 字节码长度 四个字节 但由于虚拟机限制，实际只用到65535条字节码
     */
    private int codeLength;

    /**
     * 字节码指令流，每个Code为1个字节
     */
    private byte[] codes;

    /**
     * 异常表长度 2个字节
     */
    private int exceptionTableLength;

    /**
     * 每个成员都表示codes中的一个异常处理器
     * 每个成员长度为八个字节
     */
    private List<ExceptionTable> exceptionTables;

    private int attributesCount;

    private List<Attribute> attributes;

    public CodeAttribute(Attribute attribute, JavaClass javaClass) {

        this.javaClass = javaClass;

        this.nameIndex = attribute.getNameIndex();

        this.length = attribute.getLength();

        byte[] info = attribute.getInfo();

        int currentPos = 0;
        this.maxStack = NumberUtil.byte2ToUnsignedShort(info, currentPos);
        currentPos += 2;
        this.maxLocals = NumberUtil.byte2ToUnsignedShort(info, currentPos);
        currentPos += 2;
        this.codeLength = NumberUtil.byte4ToInt(info, currentPos);
        currentPos += 4;
        this.codes = Arrays.copyOfRange(info, currentPos, currentPos + this.codeLength);
        currentPos += this.codeLength;
        this.exceptionTableLength = NumberUtil.byte2ToUnsignedShort(info, currentPos);
        currentPos += 2;
        this.exceptionTables = new ArrayList<ExceptionTable>(this.exceptionTableLength);
        for (int i = 0; i < this.exceptionTableLength; i++) {
            ExceptionTable exceptionTable = new ExceptionTable();
            exceptionTable.setStartPc(NumberUtil.byte2ToUnsignedShort(info, currentPos));
            currentPos += 2;
            exceptionTable.setEndPc(NumberUtil.byte2ToUnsignedShort(info, currentPos));
            currentPos += 2;
            exceptionTable.setHandlerPc(NumberUtil.byte2ToUnsignedShort(info, currentPos));
            currentPos += 2;
            exceptionTable.setCatchType(NumberUtil.byte2ToUnsignedShort(info, currentPos));
            currentPos += 2;
            this.exceptionTables.add(exceptionTable);
        }
        this.attributesCount = NumberUtil.byte2ToUnsignedShort(info, currentPos);
        currentPos += 2;
        this.attributes = new ArrayList<Attribute>(this.attributesCount);
        for (int i = 0; i < this.attributesCount; i++) {
            Attribute attr = new Attribute(info, currentPos);
            currentPos += attr.getLength() + 6;
            this.attributes.add(attr);
        }

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("操作数栈深度为：").append(this.maxStack)
                .append("\n局部变量表所需存储空间为:").append(this.maxLocals)
                .append("\n字节码长度为：").append(this.codeLength)
                .append("\n异常表长度为：").append(this.exceptionTableLength);
        for(int i = 0; i < this.attributesCount; i++) {
            sb.append("\n属性表").append(i).append("为：");
            sb.append(new String(javaClass.getConstantPool().get(this.attributes.get(i).getNameIndex()).getBytes()));
        }
        return sb.toString();
    }

    private class ExceptionTable {

        /**
         * startPc和endPc表示异常处理器在codes中的有效范围
         * 均为两个字节
         */
        private int startPc;

        private int endPc;

        /**
         * 异常处理器的起点 两个字节
         */
        private int handlerPc;

        /**
         * 如果catchType为0，则只能是对常量池表的有效索引，表示当前异常处理器需要捕捉的异常类型
         * 两个字节
         */
        private int catchType;

        public int getStartPc() {
            return startPc;
        }

        public void setStartPc(int startPc) {
            this.startPc = startPc;
        }

        public int getEndPc() {
            return endPc;
        }

        public void setEndPc(int endPc) {
            this.endPc = endPc;
        }

        public int getHandlerPc() {
            return handlerPc;
        }

        public void setHandlerPc(int handlerPc) {
            this.handlerPc = handlerPc;
        }

        public int getCatchType() {
            return catchType;
        }

        public void setCatchType(int catchType) {
            this.catchType = catchType;
        }
    }
}
