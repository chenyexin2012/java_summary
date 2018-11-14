package com.holmes.adapter;

import org.junit.Test;

public class AdapterTest {

    @Test
    public void test() {

        Mainframe mainframe = new Mainframe(new HDMIAdapter());
        mainframe.show();
    }
}
