### java.util.concurrent包

    demo: com.holmes.concurrency.concurrent
    
### 原子操作类

存在于java.util.concurrent.atomic包中。

#### 用于操作基本类型原子操作类

    AtomicBoolean、AtomicInteger、AtomicLong
    
#### 用于操作数组的原子操作类

    AtomicIntegerArray、AtomicLongArray、AtomicReferenceArray
    
#### 用于操作引用的原子操作类

    AtomicReference、AtomicIntegerFieldUpdater、AtomicMarkableReference
    
#### 用于操作字段的原子操作类

    AtomicIntegerFieldUpdater、AtomicLongFieldUpdater、AtomicStampedReference
    
    

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