package com.holmes.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象的中介者角色
 */
public abstract class AbstractMediator {

    protected List<AbstractPerson> landlordList = new ArrayList<AbstractPerson>();

    protected List<AbstractPerson> renterList = new ArrayList<AbstractPerson>();

    public void registerLandlord(AbstractPerson landlord){
        landlordList.add(landlord);
    }

    public void registerRenter(AbstractPerson landlord){
        renterList.add(landlord);
    }

    public abstract void operation(AbstractPerson person, String message);
}
