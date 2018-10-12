
### Semaphore 信号量

Semaphore是一个计数信号量，用户控制访问某个资源的线程数量，且只能有获取它的线程释放。

通过acquire()方法获取许可，通过release()方法释放许可，且获取许可之后必须有释放的过程。

当不希望过多的线程访问某个资源时，可以通过使用Semaphore来进行控制，代码com.java.multithread.semaphore.ConnectionManager

构造方法有两个：

        public Semaphore(int permits) {
            sync = new NonfairSync(permits);
        }
    
        public Semaphore(int permits, boolean fair) {
            sync = fair ? new FairSync(permits) : new NonfairSync(permits);
        }
        
        公平模式的信号量：fair->true，获取方式为FIFO
        非公平模式的信号量：不指定fair或fair->false，抢占式获取
        permits为1时，功能与lock相似。

### CyclicBarrier 障碍器

当用户希望在一组并发任务都结束后，执行某个任务，可以通过使用障碍器来实现。



### 创建线程池的方式

1.使用java.util.concurrent.Executors提供的静态方法创建线程池，如：

- Executors.newSingleThreadExecutor();

- Executors.newFixedThreadPool(10);

- Executors.newCachedThreadPool();

2.直接使用java.util.concurrent.ThreadPoolExecutor创建线程池，使用Executors的静态方法实际也是调用ThreadPoolExecutor的构造方法创建线程池，推荐使用ThreadPoolExecutor。
    
ThreadPoolExecutor参数说明：

1.corePoolSize：表示一个线程池的核心线程数，当活线程数小于corePoolSize，每提交一个任务就会开启一个线程来处理。

2.maximumPoolSize：maximumPoolSize>=corePoolSize，表示线程池中最多能够活跃的线程数，当workQueue长度有限且放入线程数量已满时，会继续开启线程，直到活跃线程数超过maximumPoolSize。因此，当workQueue的未指定长度时，maximumPoolSize的值将无效，因为workQueue不会满。

3.keepAliveTime：表示超过corePoolSize数量的线程处于空闲状态的最长时间，可以allowCoreThreadTimeOut(true)使得keepAliveTime可以作用于活跃线程。

4.timeUnit：keepAliveTime的时间单位，如TimeUnit.MINUTES，表示分钟。

5.workQueue：阻塞任务队列

6.ThreadFactory：是一个线程工厂，可以自定义生成的线程，如自定义线程名称。

7.RejectedExecutionHandler：当提交的任务数超过maxmumPoolSize与workQueue之和时，如何处理任务会交给RejectedExecutionHandler来进行。

    
线程池中活跃线程数量的可能性（以activeCount表示活跃线程数，taskCount表示提交任务数）：
	
1.taskCount <= corePoolSize --> activeCount = taskCount;

2.taskCount > corePoolSize && !workQueue.ifFull --> activeCount = corePoolSize;

3.taskCount > corePoolSize && taskCount < (workQueue.size + maxmumPoolSize) && workQueue.ifFull --> taskCount - workQueue.size

4.taskCount > workQueue.size + maxmumPoolSize --> maxmumPoolSize