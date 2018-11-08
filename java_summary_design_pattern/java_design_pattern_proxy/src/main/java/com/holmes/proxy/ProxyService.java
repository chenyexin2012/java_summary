package com.holmes.proxy;

/**
 * 代理类
 * 在代理模式中，代理对象对被代理对象具有控制权
 * 可以决定被代理对象的方法是否执行
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
