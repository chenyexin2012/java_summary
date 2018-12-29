package com.holmes.state;

/**
 * @Description: 共享单车
 * @Author: holmes
 * @Version: 1.0.0
 */
public class SharingBicycle {

    private State freeState = null;

    private State inUseState = null;

    private State orderedState = null;

    private State state = null;

    public SharingBicycle() {

        freeState = new FreeState(this);
        inUseState = new InUseState(this);
        orderedState = new OrderedState(this);

        state = freeState;
    }

    public void changeToFreeState() {
        this.state = this.freeState;
    }

    public void changeToOrderedState() {
        this.state = this.orderedState;
    }

    public void changeToInUseState() {
        this.state = this.inUseState;
    }

    /**
     * 预定
     */
    public void order() {
        this.state.order();
    }

    /**
     * 取消预定
     */
    public void cancel() {
        this.state.cancel();
    }

    /**
     * 解锁
     */
    public void unlock() {
        this.state.unlock();
    }

    /**
     * 归还
     */
    public void giveBack() {
        this.state.giveBack();
    }

    public State getCurrentState() {
        return this.state;
    }
}
