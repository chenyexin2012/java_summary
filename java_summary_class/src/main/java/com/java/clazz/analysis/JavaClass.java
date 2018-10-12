package com.java.clazz.analysis;

import com.java.clazz.analysis.constant.ConstantInfo;

import java.util.List;

public class JavaClass {

    private String magic;

    private int minorVersion;

    private int majorVersion;

    private int constantPoolCount;

    private List<ConstantInfo> constantPool;

    private int classAccessFlag;

    private int thisClassIndex;

    private int superClassIndex;

    private int interfacesCount;

    private List<Integer> interfaceIndexList;

    private int fieldsCount;

    public String getMagic() {
        return magic;
    }

    public void setMagic(String magic) {
        this.magic = magic;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public int getConstantPoolCount() {
        return constantPoolCount;
    }

    public void setConstantPoolCount(int constantPoolCount) {
        this.constantPoolCount = constantPoolCount;
    }

    public List<ConstantInfo> getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(List<ConstantInfo> constantPool) {
        this.constantPool = constantPool;
    }

    public int getClassAccessFlag() {
        return classAccessFlag;
    }

    public void setClassAccessFlag(int classAccessFlag) {
        this.classAccessFlag = classAccessFlag;
    }

    public int getThisClassIndex() {
        return thisClassIndex;
    }

    public void setThisClassIndex(int thisClassIndex) {
        this.thisClassIndex = thisClassIndex;
    }

    public int getSuperClassIndex() {
        return superClassIndex;
    }

    public void setSuperClassIndex(int superClassIndex) {
        this.superClassIndex = superClassIndex;
    }

    public int getInterfacesCount() {
        return interfacesCount;
    }

    public void setInterfacesCount(int interfacesCount) {
        this.interfacesCount = interfacesCount;
    }

    public List<Integer> getInterfaceIndexList() {
        return interfaceIndexList;
    }

    public void setInterfaceIndexList(List<Integer> interfaceIndexList) {
        this.interfaceIndexList = interfaceIndexList;
    }

    public int getFieldsCount() {
        return fieldsCount;
    }

    public void setFieldsCount(int fieldsCount) {
        this.fieldsCount = fieldsCount;
    }
}
