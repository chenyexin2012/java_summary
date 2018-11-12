package com.holmes.builder;

public enum GraphicsCard {

    NVIDIA("NVIDIA"), AMD("AMD");

    private String title;

    GraphicsCard(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
