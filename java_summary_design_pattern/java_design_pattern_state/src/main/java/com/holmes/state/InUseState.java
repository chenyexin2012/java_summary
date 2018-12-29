package com.holmes.state;

/**
 * @Description: 使用中
 * @Author: holmes
 * @Version: 1.0.0
 */
public class InUseState implements State {

    private SharingBicycle sharingBicycle;

    public InUseState(SharingBicycle sharingBicycle) {
        this.sharingBicycle = sharingBicycle;
    }

    @Override
    public void order() {
        // do nothing
    }

    @Override
    public void cancel() {
        // do nothing
    }

    @Override
    public void unlock() {
        // do nothing
    }

    @Override
    public void giveBack() {
        System.out.println("归还成功。。。");
        this.sharingBicycle.changeToFreeState();
    }

    @Override
    public String toString() {
        return "使用中";
    }
}
