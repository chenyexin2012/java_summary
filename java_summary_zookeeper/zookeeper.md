## Zookeeper客户端工具

### org.apache.zookeeper.ZooKeeper

org.apache.zookeeper.ZooKeeper客户端主要提供了如下几种方法：

#### create: 新增节点

    public String create(final String path, byte data[], List<ACL> acl,
                CreateMode createMode) throws KeeperException, InterruptedException
                
    public void create(final String path, byte data[], List<ACL> acl,
                CreateMode createMode,  StringCallback cb, Object ctx)

- path: 节点路径
- data： 节点数据
- acl： 节点的权限
- createMode：节点类型（PERSISTENT、PERSISTENT_SEQUENTIAL、EPHEMERAL、EPHEMERAL_SEQUENTIAL、CONTAINER、PERSISTENT_WITH_TTL、PERSISTENT_SEQUENTIAL_WITH_TTL）
- cb: 异步回调函数
- ctx: 用于传递上下文信息的对象
        
#### delete: 删除节点

    public void delete(final String path, int version)
            throws InterruptedException, KeeperException
            
    public void delete(final String path, int version, VoidCallback cb,
                Object ctx)

- path: 节点路径
- version： 节点数据版本号
- cb: 异步回调函数
- ctx: 用于传递上下文信息的对象

若删除节点存在子节点，则会抛出异常或者通过异步回调返回对应的return code：

    org.apache.zookeeper.KeeperException$NotEmptyException: KeeperErrorCode = Directory not empty for /node1
    
    rc: -111, path: /node1, ctx: null

#### getData: 获取节点数据

    public byte[] getData(final String path, Watcher watcher, Stat stat)
            throws KeeperException, InterruptedException

    public byte[] getData(String path, boolean watch, Stat stat)
                throws KeeperException, InterruptedException
     
    public void getData(final String path, Watcher watcher,
                DataCallback cb, Object ctx)
                
    public void getData(String path, boolean watch, DataCallback cb, Object ctx)
    
- path: 节点路径
- watcher： 监听器
- watch: 是否监听此节点
- stat: 用户传递节点状态信息
- cb: 异步回调函数
- ctx: 用于传递上下文信息的对象
- byte[]: 节点内容


#### setData: 修改节点内容

     public Stat setData(final String path, byte data[], int version)
            throws KeeperException, InterruptedException
            
     public void setData(final String path, byte data[], int version,
                 StatCallback cb, Object ctx)

- path: 节点路径
- data： 节点数据
- version： 节点数据版本号
- cb: 异步回调函数
- ctx: 用于传递上下文信息的对象        
- Stat: 节点状态信息        
                 
#### exists: 判断节点是否存在

    public Stat exists(final String path, Watcher watcher)
            throws KeeperException, InterruptedException

    public Stat exists(String path, boolean watch) throws KeeperException,
            InterruptedException
            
    public void exists(final String path, Watcher watcher,
                StatCallback cb, Object ctx)

    public void exists(String path, boolean watch, StatCallback cb, Object ctx)
    
- path: 节点路径
- watcher： 监听器
- watch: 是否监听此节点
- cb: 异步回调函数
- ctx: 用于传递上下文信息的对象
- Stat: 节点状态信息      

#### 存在的问题

直接使用ZooKeeper自带的客户端存在以下几种问题：

1) Watcher是一次性的，用过之后需要重新注册；
2) session超时后没有自动重连机制；
3) 没有领导选举机制，集群情况下可能需要实现stand by，一个服务挂了，另一个需要接替的效果；
4) 客户端只提供了存储byte数组的接口，而项目中一般都会使用对象。
5) 客户端接口需要处理的异常太多，并且通常，我们也不知道如何处理这些异常。


### ZkClient

ZkClient在ZooKeeper自带的客户端的基础上主要实现了以下功能：
- 在session loss或session expire时自动创建新的ZooKeeper对象进行重连。
- 使用IZkDataListener、IZkChildListener、IZkStateListener提供事件监听的功能，无需关心反复注册watcher的问题。
- 对节点中存储的内容进行序列化。
- 对Zookeeper一些接口进行封装与改造，并增加一些接口简化操作。


### Curator

Curator是Netflix公司开源的一个Zookeeper客户端，后捐献给Apache基金会，Curator框架在zookeeper原生API接口上进行了包装，
解决了很多ZooKeeper客户端非常底层的细节开发。提供分布式锁服务、集群领导选举、共享计数器、缓存机制、分布式队列等应用场景的
抽象封装。

#### Curator的监听器

Curator除了使用Watcher监听节点外，还提供了三种监听方式：

- NodeCache: 监控指定路径的节点，当该节点发生增删改时，触发。
- PathChildrenCache: 监控指定路径的节点的子节点，当新增、删除、修改字节点时，触发。
- TreeCache: 可以监控整个树上的所有节点，相当于NodeCache和PathChildrenCache的组合。

#### Curator分布式锁

- InterProcessMutex: 可重入排它锁
- InterProcessSemaphoreMutex: 不可重入
- InterProcessReadWriteLock: 分布式读写锁
- InterProcessMultiLock: 将多个锁作为单个锁的管理容器
















