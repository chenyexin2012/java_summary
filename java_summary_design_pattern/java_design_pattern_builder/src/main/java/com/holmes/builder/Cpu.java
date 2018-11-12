package com.holmes.builder;

public enum Cpu {

    PENTIUM("Pentium"), CELERON("Celeron"), CORE("Core");

    private String title;

    Cpu(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
