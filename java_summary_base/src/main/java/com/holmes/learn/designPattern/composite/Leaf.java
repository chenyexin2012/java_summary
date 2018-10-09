package com.holmes.learn.designPattern.composite;

/**
 * 叶子构件，不再存在子构件，因此只需要实现对应的业务处理方法。
 *
 * @author Administrator
 */
class Leaf extends Component {

    public void add(Component c) {
        //异常处理或错误提示   
    }

    public void remove(Component c) {
        //异常处理或错误提示   
    }

    public Component getChild(int i) {
        //异常处理或错误提示  
        return null;
    }

    public void operation() {
        //叶子构件具体业务方法的实现  
    }
}