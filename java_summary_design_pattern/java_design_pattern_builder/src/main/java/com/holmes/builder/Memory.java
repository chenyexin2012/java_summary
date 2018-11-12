package com.holmes.builder;

public enum Memory {

    HYPERX("HyperX"), GAMER("Gamer");

    private String title;

    Memory(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

}
