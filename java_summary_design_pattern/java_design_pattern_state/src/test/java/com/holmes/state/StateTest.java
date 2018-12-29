package com.holmes.state;

import org.junit.Test;

public class StateTest {

    @Test
    public void stateTest() {

        SharingBicycle sharingBicycle = new SharingBicycle();
        sharingBicycle.order();
        sharingBicycle.unlock();
        sharingBicycle.giveBack();

        sharingBicycle.unlock();
        sharingBicycle.order();
        sharingBicycle.giveBack();

        sharingBicycle.giveBack();

        System.out.println(sharingBicycle.getCurrentState());
    }
}
