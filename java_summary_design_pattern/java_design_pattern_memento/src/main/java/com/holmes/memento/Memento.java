package com.holmes.memento;


/**
 * @Description: 备忘录
 * @Author: holmes
 * @Version: 1.0.0
 */
public class Memento {

    private String name;

    private int level;

    private int lp;

    public Memento(String name, int level, int lp) {
        this.name = name;
        this.level = level;
        this.lp = lp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }
}
