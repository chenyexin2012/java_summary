package com.holmes.mediator;

import org.junit.Test;

public class MediatorTest {

    @Test
    public void mediatorTest() {

        AbstractMediator mediator = new HouseMediator();

        AbstractPerson landlordA = new Landlord("landlordA", mediator);
        AbstractPerson landlordB = new Landlord("landlordB", mediator);

        AbstractPerson renterA = new Renter("renterA", mediator);
        AbstractPerson renterB = new Renter("renterB", mediator);


        mediator.registerLandlord(landlordA);
        mediator.registerLandlord(landlordB);

        mediator.registerRenter(renterA);
        mediator.registerRenter(renterB);

        landlordA.sendMessage("A小区1栋有房屋出租");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
        landlordB.sendMessage("B小区2栋有房屋出租");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
        renterA.sendMessage("想要在A小区租房");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
        renterB.sendMessage("想要在B小区租房");

    }
}
