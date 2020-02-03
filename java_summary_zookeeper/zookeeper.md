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
- createMode：节点类型（PERSISTENT、PERSISTENT_SEQUENTIAL、EPHEMERAL、EPHEMERAL_SEQUENTIAL）
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