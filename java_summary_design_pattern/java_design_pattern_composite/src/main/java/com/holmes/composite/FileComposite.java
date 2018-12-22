package com.holmes.composite;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: holmes
 * @CreateDate: 2018/12/22 14:11
 * @Version: 1.0.0
 */
public abstract class FileComposite {

    protected String name;

    private List<FileComposite> children = new LinkedList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void addChild(FileComposite child) {
        children.add(child);
    }

    public void beforeDisplay() {
    }

    public void afterDisplay() {
    }

    public void display() {
        beforeDisplay();
        for (FileComposite child : children) {
            child.display();
        }
        afterDisplay();
    }
}
