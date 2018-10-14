package com.java.summary.stream;

import java.io.Serializable;

public class MyObject implements Serializable {

    private static final long serialVersionUID = 5020138090120032854L;

    private String strField;
    private Long longField;
    private Double doubleField;
    private transient String transientFiled;

    public String getStrField() {
        return strField;
    }

    public void setStrField(String strField) {
        this.strField = strField;
    }

    public Long getLongField() {
        return longField;
    }

    public void setLongField(Long longField) {
        this.longField = longField;
    }

    public Double getDoubleField() {
        return doubleField;
    }

    public void setDoubleField(Double doubleField) {
        this.doubleField = doubleField;
    }

    public String getTransientFiled() {
        return transientFiled;
    }

    public void setTransientFiled(String transientFiled) {
        this.transientFiled = transientFiled;
    }

    @Override
    public String toString() {
        return "MyObject{" +
                "strField='" + strField + '\'' +
                ", longField=" + longField +
                ", doubleField=" + doubleField +
                ", transientFiled='" + transientFiled + '\'' +
                '}';
    }
}
