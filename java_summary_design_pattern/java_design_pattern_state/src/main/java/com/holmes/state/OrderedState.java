package com.holmes.state;

/**
 * @Description: 已被预订
 * @Author: holmes
 * @Version: 1.0.0
 */
public class OrderedState implements State {

    private SharingBicycle sharingBicycle;

    public OrderedState(SharingBicycle sharingBicycle) {
        this.sharingBicycle = sharingBicycle;
    }

    @Override
    public void order() {
        // do nothing
    }

    @Override
    public void cancel() {
        System.out.println("取消预定成功。。。");
        this.sharingBicycle.changeToFreeState();
    }

    @Override
    public void unlock() {
        System.out.println("开锁成功。。。");
        this.sharingBicycle.changeToInUseState();
    }

    @Override
    public void giveBack() {
        // do nothing
    }

    @Override
    public String toString() {
        return "已预订";
    }
}
