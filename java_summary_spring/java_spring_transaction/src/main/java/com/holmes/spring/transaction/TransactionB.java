package com.holmes.spring.transaction;

import com.holmes.spring.dao.UserDao;
import com.holmes.spring.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionB {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    /**
     * 当前方法必须运行在事务中。如果当前事务存在，方法将会在该事务中运行。否则，会启动一个新的事务
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void requiredTx(User user) {
        getCurrentSession().save(user);
    }

    /**
     * 表示当前方法必须运行在它自己的事务中。一个新的事务将被启动。如果存在当前事务，在该方法执
     * 行期间，当前事务会被挂起。如果使用JTATransactionManager的话，则需要访问TransactionManager。
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiredNewTx(User user) {
        getCurrentSession().save(user);
    }

    /**
     * 表示该方法必须在事务中运行，如果当前事务不存在，则会抛出一个异常。
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatoryTx(User user) {
        getCurrentSession().save(user);
    }

    /**
     * 表示当前方法不需要事务上下文，但是如果存在当前事务的话，那么该方法会在这个事务中运行。
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void supportsTx(User user) {
        getCurrentSession().save(user);
    }

    /**
     * 表示该方法不应该运行在事务中。如果存在当前事务，在该方法运行期间，当前事务将被挂起。
     * 如果使用JTATransactionManager的话，则需要访问TransactionManager。
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void notSupportedTx(User user) {
        getCurrentSession().save(user);
    }

    /**
     * 表示当前方法不应该运行在事务上下文中。如果当前正有一个事务在运行，则会抛出异常。
     */
    @Transactional(propagation = Propagation.NEVER)
    public void neverTx(User user) {
        getCurrentSession().save(user);
    }

    /**
     * 表示如果当前已经存在一个事务，那么该方法将会在嵌套事务中运行。嵌套的事务可以独立于当前事务
     * 进行单独地提交或回滚。如果当前事务不存在，那么其行为与PROPAGATION_REQUIRED一样。注意各厂商
     * 对这种传播行为的支持是有所差异的。可以参考资源管理器的文档来确认它们是否支持嵌套事务。
     */
    @Transactional(propagation = Propagation.NESTED)
    public void nestedTx(User user) {
        getCurrentSession().save(user);
    }

}
