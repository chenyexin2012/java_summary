### java.util.concurrent包

    demo: com.holmes.concurrency.concurrent
    
### 原子操作类

存在于java.util.concurrent.atomic包中。

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