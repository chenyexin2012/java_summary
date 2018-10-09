package com.java.summary.mybatis.plugins;

public class Page {

    private int offset;

    private int limit;

    public Page() {
        this(0, Integer.MAX_VALUE);
    }

    public Page(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
