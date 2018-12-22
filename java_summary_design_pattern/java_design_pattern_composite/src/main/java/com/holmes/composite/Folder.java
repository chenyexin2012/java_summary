package com.holmes.composite;

/**
 * @Description: java类作用描述
 * @Author: holmes
 * @CreateDate: 2018/12/22 14:17
 * @Version: 1.0.0
 */
public class Folder extends FileComposite {

    public Folder(String name) {
        this.name = name;
    }

    @Override
    public void afterDisplay() {
        System.out.print("] ");
    }

    @Override
    public void beforeDisplay() {
        System.out.print(" " + this.name + " : [");
    }
}
