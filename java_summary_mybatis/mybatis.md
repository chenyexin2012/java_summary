### mybatis的缓存

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