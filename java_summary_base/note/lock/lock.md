### Lock

    public interface Lock {
        void lock();
        void lockInterruptibly() throws InterruptedException;
        boolean tryLock();
        boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
        void unlock();
        Condition newCondition();
    }

    1.lock()方法：
        用于获取锁，如果锁被其他线程占有，则等待。
        一般使用方式：
            Lock lock = ...;
            try {
                // 获取锁
                lock.lock();
                doSomething();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                lock.unlock();
            }
    
    2.tryLock()方法：
        有返回值，使用tryLock方法会去尝试获取锁，获取成功放回true，否则返回false，不会一直处于等待状态。
        一般使用方式：
            Lock lock = ...;
            if(lock.tryLock()) {
                try {
                    doSomething();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            } else {
                doOtherThings();
            }
        
    3.tryLock(long time, TimeUnit unit)方法与tryLock()方法类似，只是加上了一个时间限制，在指定一段时间内能否成功获取锁，获取失败则
    返回false。
    
    4.lockInterruptibly()方法：
        当使用lockInterruptibly方法获取锁时一直处于等待中，可以通过使用interrupt()方法中断该线程的等待过程。
        该方法声明了异常InterruptedException。
        一般使用方式：
        Lock lock = ...;
        try {
            lock.lockInterruptibly();
            doSomething();
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        

#### ReentrantLock 可重入锁
     
    public class ReentrantLock implements Lock, java.io.Serializable
    
    1.  上锁：lock()
        解锁：unlock()
        构造方法：公平锁/非公平锁
            public ReentrantLock() {
               sync = new NonfairSync();
            }
            public ReentrantLock(boolean fair) {
                sync = fair ? new FairSync() : new NonfairSync();
            }
    
        ReentrantLock的内部类：FairSync、NonfairSync、Sync
            1.Sync-->AbstractQueuedSynchronizer-->AbstractOwnableSynchronizer
            2.static final class FairSync extends Sync
            3.static final class NonfairSync extends Sync
        
    2.公平锁/非公平锁

        公平锁：先进来的线程先执行
        非公平锁：后进来的线程也可能先执行
        
        ReentrantLock既支持非公平锁，也支持公平锁
            ReentrantLock lock = new ReentrantLock();（默认非公平锁）
            ReentrantLock lock = new ReentrantLock(true)
    
    3.ReentrantLock获取公平锁/非公平锁的方法差异
        非公平锁：NonfairSync#tryAcquire-->Sync#nonfairTryAcquire
            if (c == 0) {
                // 独占锁线程已释放锁，当前线程再次尝试获取
                if (compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
        公平锁：FairSync#tryAcquire
        if (c == 0) {
            // 当前线程是等待队列中的头节点
            // 且基于CAS尝试将state（锁数量）从0设置为1成功
            if (!hasQueuedPredecessors() &&
                compareAndSetState(0, acquires)) {
                setExclusiveOwnerThread(current);
                return true;
            }
        }
    由这部分代码可知，在使用公平锁获取锁的过程中，需要判断当前线程是否在等待队列的首部，而在非公平锁中，可以随时“插队”。
    
    
    参考博文：
        http://www.cnblogs.com/java-zhao/p/5131544.html
        https://www.cnblogs.com/java-zhao/p/5133402.html
        https://www.cnblogs.com/dolphin0520/p/3923167.html
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    