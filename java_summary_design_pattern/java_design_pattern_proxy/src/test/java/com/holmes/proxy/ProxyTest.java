package com.holmes.proxy;

import org.junit.Test;

public class ProxyTest {

    @Test
    public void test() {

        IService proxy = new ProxyService(new TargetService());
        proxy.visitHolmes(new User("Watson"));
        proxy.visitHolmes(new User("Adler"));
        proxy.visitHolmes(new User("Moriarty"));
    }
}
