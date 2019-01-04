## 线程

    demo：com.holmes.concurrency.thread
    
### 线程的状态

- 创建：生成一个线程对象，还没有调用start方法。

- 就绪：调用了线程对象的start方法之后，该线程就进入了就绪状态，但是此时线程调度程序还没有把该线程设置为当前线程，此时处于就绪状态。在线程运行之后，从等待或者睡眠中回来之后，也会处于就绪状态。

- 运行：线程调度程序将处于就绪状态的线程设置为当前线程，此时线程就进入了运行状态，开始运行run函数当中的代码。

- 阻塞：线程正在运行的时候，被暂停，通常是为了等待某个时间的发生(比如说某项资源就绪)之后再继续运行。sleep、suspend、wait等方法都可以导致线程阻塞。

- 结束：如果一个线程的run方法执行结束或者调用stop方法后，该线程就会死亡。对于已经死亡的线程，无法再使用start方法令其进入就绪。

### java实现线程的方式

在java中实现一个线程常用的方法有两种：继承Thread类、实现Runnable接口。但在开发过程中一般推荐使用第二种方式。
当需要实现一个带有返回结果的线程时，还可以通过使用ExecutorService、Callable、Future来完成。
        
### Thread的几个重要方法

    // 启动线程
    public synchronized void start()
    
    // 中断线程，实际上并不立即中断线程，而是将线程的中断标志置为true。
    public void interrupt()
  
    // 返回线程的中断状态
    public boolean isInterrupted()
    
    // Thread的静态方法，返回当前线程
    public static native Thread currentThread()
    
    // Thread的静态方法，返回当前线程的中断状态，并将中断状态置为false。
    public static boolean interrupted()
    
    // 线程是否为守护线程，返回ture则为守护线程，返回false则为用户线程
    public final boolean isDaemon()
    
    // 放弃执行当前线程，并等待调用join方法的线程执行完毕
    public final void join() throws InterruptedException
    
    // Thread的静态方法，将当前线程由运行状态转变为就绪状态，即放弃自己的CPU时间，让自己或其它线程执行
    public static native void yield();

### 用户线程与守护线程

- 用户线程（User Thread）：由用户建立且独立运行的线程，不会因为其它用户线程的退出而退出。

- 守护线程（Daemon Thread）：运行于后台的线程，主要为用户线程提供服务，一旦用户线程全部退出，守护线程也会退出，如GC。

### 线程阻塞

线程阻塞一般发生于一下几种情况：

- 线程中执行Thread.sleep()方法使当前线程进入休眠状态，此时会阻塞至指定时长之后或者响应一个中断。

- 线程中遇到对象的wait方法，会阻塞至接收到notify或notifyAll通知、经过指定时长或者响应一个中断。

- I/O阻塞。

- 等待获取某个对象锁的排他性访问权限。

### 线程的优先级

        Thread中定义了线程的优先级：
        
        /**
         * The minimum priority that a thread can have.
         */
        public final static int MIN_PRIORITY = 1;
    
       /**
         * The default priority that is assigned to a thread.
         */
        public final static int NORM_PRIORITY = 5;
    
        /**
         * The maximum priority that a thread can have.
         */
        public final static int MAX_PRIORITY = 10;
        
java中线程的优先级的取值范围为MIN_PRIORITY(1)至MAX_PRIORITY(10)，默认为NORM_PRIORITY(5)，优先级值越大则线程会优先执行。

## 线程的取消与中断



## 线程池

### 线程池的生命周期

- 运行
- 关闭
- 已终止

### 几个重要的接口

#### Executor
    
    public interface Executor {
        void execute(Runnable command);
    }

#### ExecutorService

    public interface ExecutorService extends Executor {
        
        // 不再接收新的任务，所有任务执行完成后关闭ExecutorService
        void shutdown();
    
        // 不再接收新的任务，立即关闭ExecutorService并返回没有执行的任务列表
        List<Runnable> shutdownNow();
    
        // 是否关闭
        boolean isShutdown();
    
        // 是否终止
        boolean isTerminated();
 
        // 等待指定时间，若在此时间内，所有任务执行完毕，则立即返回true
        // 否则返回false
        boolean awaitTermination(long timeout, TimeUnit unit)
            throws InterruptedException;
    
        // 提交一个任务，可通过Future.get()获取返回值
        <T> Future<T> submit(Callable<T> task);
    
        // 提交一个任务，且在任务执行完成后可通过Future.get()获取result
        <T> Future<T> submit(Runnable task, T result);
    
        // 提交一个任务，且在任务执行完成后Future.get()返回null
        Future<?> submit(Runnable task);
    
        // 批量提交任务，并且获取它们的future，future与提交的task一一对应
        <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
            throws InterruptedException;
    
        // 批量提交任务，并且获取它们的future，future与提交的task一一对应，
        // 当任务超时时，该任务结束，Future.isCancelled()返回true
        <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
                                      long timeout, TimeUnit unit)
            throws InterruptedException;
    
        // 批量提交任务，并且获取一个成功执行的任务的返回结果
        <T> T invokeAny(Collection<? extends Callable<T>> tasks)
            throws InterruptedException, ExecutionException;
    
        // 批量提交任务，并且获取限定时间内一个成功执行的任务的返回结果
        <T> T invokeAny(Collection<? extends Callable<T>> tasks,
                        long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException;
    }

### 创建线程池的方式

- 使用Executors的静态方法创建线程池。
- 直接使用ThreadPoolExecutor手动创建线程池（推荐）。

### 几种常用的线程池

1.单个工作线程的线程池 

ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
    }
    
- 启动一个单独的工作线程。


2.指定工作线程数量的线程池

ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
    }
    
3.可缓存线程池

ExecutorService executorService = Executors.newCachedThreadPool();

    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());
    }

- 创建工作线程的数量没有限制，为Integer.MAX_VALUE。
- 指定工作线程的空闲时间为60s。

4.支持定时及周期性任务执行的定长线程池

ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    public ScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
              new DelayedWorkQueue());
    }
    
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
             Executors.defaultThreadFactory(), defaultHandler);
    }


### ThreadPoolExecutor

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

### 判断线程池是否终止

1.使用shutdown方法和isTerminated方法。

    threadPoolExecutor.shutdown();
    while(!threadPoolExecutor.isTerminated()) {
    }
    System.out.println("all threads has finished ...");
    
2.使用shutdown方法和iawaitTermination方法。

    threadPoolExecutor.shutdown();
    while(!threadPoolExecutor.isTerminated()) {
    }
    System.out.println("all threads had finished ...");
       
