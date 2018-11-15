package com.holmes.facade;

import org.junit.Test;

public class FacadeTest {

    @Test
    public void facadeTest() {

        ComputerFacade computerFacade = new ComputerFacade();
        computerFacade.startComputer();
        computerFacade.shutdownComputer();
    }
}
