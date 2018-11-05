
### Mapper接口的“实例化”
在我们使用mybatis时，会定义很多Mapper接口，用来对应着我们需要执行的sql语句。但是，Mapper作为一个接口，我们无法直接对其进行实例化。
因此，mybatis提供了MapperProxy类来间接的实现Mapper接口的实例化。

SqlSession接口提供了一个方法：

    /**
     * Retrieves a mapper.
     * @param <T> the mapper type
     * @param type Mapper interface class
     * @return a mapper bound to this SqlSession
     */
    <T> T getMapper(Class<T> type);
    
通过调用这个方法可以获取对应Mapper接口的代理对象，当我们调用Mapper接口中的方法时，就会接着调用下面这个方法，从而定位到正确的SqlSession方法上：

    org.apache.ibatis.binding.MapperProxy
    /**
     * 调用mapper中的方法时，实际调用处
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (Object.class.equals(method.getDeclaringClass())) {
                // Object中提供的方法，直接执行
                return method.invoke(this, args);
            } else if (isDefaultMethod(method)) {
                return invokeDefaultMethod(proxy, method, args);
            }
        } catch (Throwable t) {
            throw ExceptionUtil.unwrapThrowable(t);
        }
        // 其它方法由MapperMethod来执行
        // 若没有对应的MapperMethod，则新建一个并加入MapperMethod缓存中
        final MapperMethod mapperMethod = cachedMapperMethod(method);
        return mapperMethod.execute(sqlSession, args);
    }

### SqlSession和SqlSessionFactory
SqlSession接口是mybatis框架中最重要的接口之一，它定义了我们对数据库的CRUD及事务操作的方法，
而SqlSessionFactory提供了多种生成SqlSession对象的方法。

#### DefaultSqlSession和DefaultSqlSessionFactory
DefaultSqlSession和DefaultSqlSessionFactory分别实现了SqlSession接口和SqlSessionFactory接口。

以DefaultSqlSession中一段为例：
    
    public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
        try {
            MappedStatement ms = configuration.getMappedStatement(statement);
            return executor.query(ms, wrapCollection(parameter), rowBounds, Executor.NO_RESULT_HANDLER);
        } catch (Exception e) {
            throw ExceptionFactory.wrapException("Error querying database.  Cause: " + e, e);
        } finally {
            ErrorContext.instance().reset();
        }
    }

在我们通过使用代理对象调用Mapper接口中的方法时，最终都会定位到相应的SqlSession方法上，如上述代码中，
首先或根据statement从configuration中获取到包含着sql节点信息的MappedStatement对象，并交给相应Executor去执行。

而在DefaultSqlSessionFactory中，如何创建一个DefaultSqlSession对象，主要来自下面两种方式：

        private SqlSession openSessionFromDataSource(ExecutorType execType, TransactionIsolationLevel level, boolean autoCommit) {
            Transaction tx = null;
            try {
                final Environment environment = configuration.getEnvironment();
                // 获取事务工厂
                final TransactionFactory transactionFactory = getTransactionFactoryFromEnvironment(environment);
                // 通过事务工厂创建事务
                tx = transactionFactory.newTransaction(environment.getDataSource(), level, autoCommit);
                final Executor executor = configuration.newExecutor(tx, execType);
                return new DefaultSqlSession(configuration, executor, autoCommit);
            } catch (Exception e) {
                closeTransaction(tx); // may have fetched a connection so lets call close()
                throw ExceptionFactory.wrapException("Error opening session.  Cause: " + e, e);
            } finally {
                ErrorContext.instance().reset();
            }
        }
    
        private SqlSession openSessionFromConnection(ExecutorType execType, Connection connection) {
            try {
                boolean autoCommit;
                try {
                    autoCommit = connection.getAutoCommit();
                } catch (SQLException e) {
                    // Failover to true, as most poor drivers
                    // or databases won't support transactions
                    autoCommit = true;
                }
                final Environment environment = configuration.getEnvironment();
                final TransactionFactory transactionFactory = getTransactionFactoryFromEnvironment(environment);
                final Transaction tx = transactionFactory.newTransaction(connection);
                final Executor executor = configuration.newExecutor(tx, execType);
                return new DefaultSqlSession(configuration, executor, autoCommit);
            } catch (Exception e) {
                throw ExceptionFactory.wrapException("Error opening session.  Cause: " + e, e);
            } finally {
                ErrorContext.instance().reset();
            }
        }

#### SqlSessionManager
SqlSessionManager同时实现了SqlSession接口和SqlSessionFactory接口，参数和构造函数如下：

        private final SqlSessionFactory sqlSessionFactory;
        // 动态代理对象
        private final SqlSession sqlSessionProxy;
    
        private final ThreadLocal<SqlSession> localSqlSession = new ThreadLocal<>();
    
        private SqlSessionManager(SqlSessionFactory sqlSessionFactory) {
            this.sqlSessionFactory = sqlSessionFactory;
            this.sqlSessionProxy = (SqlSession) Proxy.newProxyInstance(
                    SqlSessionFactory.class.getClassLoader(),
                    new Class[]{SqlSession.class},
                    new SqlSessionInterceptor());
        }

这里的sqlSessionFactory就相当于一个DefaultSqlSessionFactory实例，而sqlSessionProxy则是一个动态代理的SqlSession对象，
因此所有调用sqlSessionProxy的方法都会经历SqlSessionInterceptor拦截器

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // 从线程局部变量中取出sqlSession
            final SqlSession sqlSession = SqlSessionManager.this.localSqlSession.get();
            if (sqlSession != null) {
                try {
                    // 直接复用线程局部变量中的sqlSession（不提交、不回滚、不关闭，让用户自定义session的提交、回滚和关闭，达到在县城内复用的效果）
                    return method.invoke(sqlSession, args);
                } catch (Throwable t) {
                    throw ExceptionUtil.unwrapThrowable(t);
                }
            } else {
                // 产生新的SqlSession实例（自动提交、回滚）
                try (SqlSession autoSqlSession = openSession()) {
                    try {
                        final Object result = method.invoke(autoSqlSession, args);
                        autoSqlSession.commit();
                        return result;
                    } catch (Throwable t) {
                        autoSqlSession.rollback();
                        throw ExceptionUtil.unwrapThrowable(t);
                    }
                }
            }
        }

### 执行器 Executor
SqlSession对数据库的操作，最终都会委托给Executor来完成，在mybatis框架中，Executor的类型公有五种，分别是：
SimpleExecutor、ReuseExecutor、ResultExtractor、CachingExecutor、BatchExecutor，它们都继承自实现了
Executor接口的BaseExecutor类。



### 一个SQL完整执行流程
在mybatis的枚举类SqlCommandType中，定义了以下几个SQL的命令类型：

    UNKNOWN, INSERT, UPDATE, DELETE, SELECT, FLUSH
    




### 事务管理机制
mybatis的Transaction接口定义：

    public interface Transaction {
        Connection getConnection() throws SQLException;
        void commit() throws SQLException;
        void rollback() throws SQLException;
        void close() throws SQLException;
        Integer getTimeout() throws SQLException;
    }
    
在mybatis中，Transaction主要有两种实现:

    使用jdbc的事务管理机制：
        org.apache.ibatis.transaction.managed.ManagedTransaction
    使用MANAGED的事务管理机制，自身不实现事务，而是将实现的任务交给其它框架或容器：
        org.apache.ibatis.transaction.jdbc.JdbcTransaction

xml中事务的配置：

    environments->environment->transactionManager节点中，如：<transactionManager type="JDBC"/>
    
    mybatis会根据type创建对应的事务工厂：
    
        private TransactionFactory transactionManagerElement(XNode context) throws Exception {
            if (context != null) {
                /**
                 * <transactionManager type="XXX"/>
                 * 根据type创建对应的事务工厂
                 */
                String type = context.getStringAttribute("type");
                Properties props = context.getChildrenAsProperties();
                TransactionFactory factory = (TransactionFactory) resolveClass(type).newInstance();
                factory.setProperties(props);
                return factory;
            }
            throw new BuilderException("Environment declaration requires a TransactionFactory.");
        }

    并且在Configuration初始化时，已经注册了对应的类型处理器：
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("MANAGED", ManagedTransactionFactory.class);
    因此在
        TransactionFactory factory = (TransactionFactory) resolveClass(type).newInstance();
    中可以直接创建工厂。


### 缓存

MyBatis执行SQL语句之后，这条语句就会被缓存，以后再执行这条语句的时候，会直接从缓存中拿结果，而不是再次执行SQL。

#### 一级缓存

作用域：session或statement，默认为session，一级缓存不提供关闭功能。
    
    配置为SESSION：在同个SqlSession中，查询语句相同的sql会被缓存，但是一旦执行新增或更新或删除操作，缓存就会被清除。
    配置为STATEMENT：则表示SqlSession范围内的一个查询范围，但它并不是一个Statement实例范围，例如在查询User对象时发送一次查询，
    紧接着查询其关联的Address对象，这个过程称为一个statement。

配置方式：

    <configuration>
        <settings>
            <setting name="localCacheScope" value="SESSION|STATEMENT" />
        </settings>
    </configuration>

例如：在同一个session中，同一个查询动作执行两次，但只会执行一次SQL。

#### 二级缓存

作用域：全局

    二级缓存在SqlSession关闭或提交之后才会生效。

开启方式：

    1.config文件中：
        cacheEnabled默认为true，此处可以不设置。
        <configuration>
            <settings>
                <setting name="cacheEnabled" value="true|false" />
            </settings>
        </configuration>
    
    2.mapper文件中：
        使用mybatis提供的缓存：
        简单的配置：<cache/>
        详细的配置如：<cache eviction="FIFO" flushInterval="60000" size="512" readOnly="true"/>
        用户自己实现的缓存：
        <cache type="XXX.XXX"/>
        XXX.XXX为实现org.apache.ibatis.cache.Cache接口的类。
        
    3.select节点中，属性useCache需要为true（默认值为true，可以不设置）
    
cache配置:
    
    eviction：回收策略
    flushInterval：刷新间隔，可为任意正整数，单位ms，默认不设置，即没有刷新间隔，缓存仅仅在调用语句时刷新。
    size：引用数目，可为任意正整数，默认1024，可依运行环境来定。
    readOnly：只读属性，默认为false，表示可读写的缓存，这样读取时会返回缓存对象的拷贝(通过序列化)，有一定的性能影响，但是安全。
    设置为true是，表示只读的缓存，会给所有调用者返回缓存对象的相同实例，因此这些对象不能被修改，具有性能优势。
    type：使用自定义缓存时配置，type属性指定的类必须实现org.apache.ibatis.cache.Cache接口，
    如<cache type="com.java.summary.redis.mybatis.cache.RedisCache"/>
    
回收策略：

    LRU – 最近最少使用的:移除最长时间不被使用的对象。
    FIFO – 先进先出:按对象进入缓存的顺序来移除它们。
    SOFT – 软引用:移除基于垃圾回收器状态和软引用规则的对象。
    WEAK – 弱引用:更积极地移除基于垃圾收集器状态和弱引用规则的对象。