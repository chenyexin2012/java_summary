package com.holmes.mediator;

/**
 * 中介者角色的具体实现
 */
public class HouseMediator extends AbstractMediator {

    @Override
    public void operation(AbstractPerson person, String message) {
        if (person instanceof Renter) {
            for (AbstractPerson landlord : landlordList) {
                landlord.getMessage(message);
            }
        } else if (person instanceof Landlord) {
            for (AbstractPerson renter : renterList) {
                renter.getMessage(message);
            }
        }
    }
}
