## 线程

### java实现线程的方式

在java中实现一个线程常用的方法有两种：继承Thread类、实现Runnable接口。但在开发过程中一般推荐使用第二种方式。
当需要实现一个带有返回结果的线程时，还可以通过使用ExecutorService、Callable、Future来完成。

    com.holmes.concurrency.thread.ThreadDemo
    com.holmes.concurrency.thread.RunnableDemo
    com.holmes.concurrency.thread.CallableDemo


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
       
