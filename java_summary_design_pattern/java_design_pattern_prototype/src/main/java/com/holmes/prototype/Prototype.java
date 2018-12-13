package com.holmes.prototype;

/**
 * @Description:
 * @Author: holmes
 * @CreateDate: 2018/12/13 18:46
 * @Version: 1.0.0
 */
public interface Prototype extends Cloneable {
    /**
     * 克隆接口
     * @return 生成类
     * @throws
     * @author holmes
     * @date 2018/12/13 18:46
     */
    Object clone() throws CloneNotSupportedException;
}
