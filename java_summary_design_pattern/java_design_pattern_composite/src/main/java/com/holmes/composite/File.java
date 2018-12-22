package com.holmes.composite;

/**
 * @Description:
 * @Author: holmes
 * @CreateDate: 2018/12/22 14:43
 * @Version: 1.0.0
 */
public class File extends FileComposite {

    public File(String name) {
        this.name = name;
    }

    @Override
    public void beforeDisplay() {
        System.out.print(" " + this.name + " ");
    }
}
