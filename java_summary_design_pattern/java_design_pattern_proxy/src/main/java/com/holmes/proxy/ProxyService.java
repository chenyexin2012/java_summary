package com.holmes.proxy;

/**
 * 代理类
 */
public class ProxyService implements IService {

    IService target = null;

    public ProxyService(IService target) {
        this.target = target;
    }

    @Override
    public void visitHolmes(User visitor) {
        if ("Moriarty".equals(visitor.getName())) {
            System.out.println("Holmes do not want to see you, " + visitor.getName() + ".");
            return;
        }
        target.visitHolmes(visitor);
    }
}
