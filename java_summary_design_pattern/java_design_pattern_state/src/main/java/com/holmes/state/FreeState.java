package com.holmes.state;

/**
 * @Description: 空闲状态
 * @Author: holmes
 * @Version: 1.0.0
 */
public class FreeState implements State {

    private SharingBicycle sharingBicycle;

    public FreeState(SharingBicycle sharingBicycle) {
        this.sharingBicycle = sharingBicycle;
    }

    @Override
    public void order() {
        System.out.println("预订成功。。。");
        this.sharingBicycle.changeToOrderedState();
    }

    @Override
    public void cancel() {
        // do nothing
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
        return "空闲";
    }
}
