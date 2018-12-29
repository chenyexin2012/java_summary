package com.holmes.state;

/**
 * @Description:
 * @Author: holmes
 * @Version: 1.0.0
 */
public interface State {

    /**
     * 预定
     */
    void order();

    /**
     * 取消预定
     */
    void cancel();

    /**
     * 解锁
     */
    void unlock();

    /**
     * 归还
     */
    void giveBack();
}
