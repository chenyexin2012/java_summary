## java并发工具包 java.util.concurrent

    demo: com.holmes.concurrency.concurrent
    
### 原子操作类

位于java.util.concurrent.atomic包中。

#### 用于操作变量的原子操作类

    AtomicBoolean、AtomicInteger、AtomicLong、AtomicReference
    
#### 用于操作数组的原子操作类

    AtomicIntegerArray、AtomicLongArray、AtomicReferenceArray
    
#### 用于操作字段的原子操作类（利用反射原理）

    AtomicIntegerFieldUpdater、AtomicLongFieldUpdater、AtomicReferenceFieldUpdater
   
使用原子操作类操作字段注意事项：

- 字段必须是volatile类型的。
- 只能操作实例变量，不能操作类变量。
- 只能操作调用者能够直接访问的字段，public、protect等修饰符修饰的字段。
- AtomicIntegerFieldUpdater/AtomicLongFieldUpdater只能操作int/long类型的字段，不能操作其包装类。
    
    
### 锁 Lock

Lock接口位于java.util.concurrent.locks包中。

    public interface Lock {
        void lock();
        void lockInterruptibly() throws InterruptedException;
        boolean tryLock();
        boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
        void unlock();
        Condition newCondition();
    }

1.lock()方法：用于获取锁，如果锁被其他线程占有，则等待。一般使用方式如下：
    
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
    
2.tryLock()方法：有返回值，使用tryLock方法会去尝试获取锁，获取成功放回true，否则返回false，不会一直处于等待状态。一般使用方式如下：
        
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
        
3.tryLock(long time, TimeUnit unit)：与tryLock()方法类似，只是加上了一个时间限制，在指定一段时间内能否成功获取锁，获取失败则返回false。
    
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

ReentrantLock是一个排他锁，同一时间只允许一个线程访问。
    
1.上锁lock()/解锁unlock()

    //构造方法：公平锁/非公平锁
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
        
        
#### ReentrantReadWriteLock 读写锁

ReentrantReadWriteLock允许多个读线程同时访问，但不允许写线程和读线程、写线程和写线程同时访问。在实际的应用场景中，一般对共享数据的读操
作要多于写操作，因此ReentrantReadWriteLock能够提供比排他锁更好的并发性和吞吐量。

ReentrantReadWriteLock中实现了两个锁：

    // 读操作相关的锁，称为共享锁
    private final ReentrantReadWriteLock.ReadLock readerLock;
    // 写相关的锁，称为排他锁
    private final ReentrantReadWriteLock.WriteLock writerLock;

    
    参考博文：
        https://www.cnblogs.com/xiaoxi/p/9140541.html

#### 条件变量 Condition 

Condition是在java 1.5中才出现的，它用来替代传统的Object的wait()、notify()实现线程间的协作，相比使用Object的wait()、notify()，
使用Condition的await()、signal()这种方式实现线程间协作更加安全和高效。

    public interface Condition {
        
        void await() throws InterruptedException;
       
        void awaitUninterruptibly();

        long awaitNanos(long nanosTimeout) throws InterruptedException;
        
        boolean await(long time, TimeUnit unit) throws InterruptedException;
        
        boolean awaitUntil(Date deadline) throws InterruptedException;
        
        void signal();
        
        void signalAll();
    }



### CyclicBarrier 障碍器

当用户希望在一组并发任务都结束后，执行某个任务，可以通过使用障碍器来实现。

### Semaphore 信号量

Semaphore是一个计数信号量，用户控制访问某个资源的线程数量，且只能有获取它的线程释放。

通过acquire()方法获取许可，通过release()方法释放许可，且获取许可之后必须有释放的过程。

当不希望过多的线程访问某个资源时，可以通过使用Semaphore来进行控制，如代码com.java.multithread.semaphore.ConnectionManager

Semaphore构造方法有两个：

        public Semaphore(int permits) {
            sync = new NonfairSync(permits);
        }
    
        public Semaphore(int permits, boolean fair) {
            sync = fair ? new FairSync(permits) : new NonfairSync(permits);
        }
        
        公平模式的信号量：fair->true，获取方式为FIFO
        非公平模式的信号量：不指定fair或fair->false，抢占式获取
        permits为1时，功能与lock相似。