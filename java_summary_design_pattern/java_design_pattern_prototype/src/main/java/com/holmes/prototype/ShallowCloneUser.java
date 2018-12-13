package com.holmes.prototype;

/**
 * @Description:
 * @Author: holmes
 * @CreateDate: 2018/12/13 19:26
 * @Version: 1.0.0
 */
public class ShallowCloneUser extends User implements ShallowClone {
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
