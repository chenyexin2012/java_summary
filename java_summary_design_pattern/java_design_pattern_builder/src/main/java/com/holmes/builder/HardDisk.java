package com.holmes.builder;

public enum HardDisk {

    TOSHIBA("Toshiba"), SEAGATE("Seagate");

    private String title;

    HardDisk(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
