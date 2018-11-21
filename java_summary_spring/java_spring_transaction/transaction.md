### Spring事务基础

####　事务的四个特性　ACID

- 原子性（Atomicity）：事务是一个原子操作，由一系列动作组成。事务的原子性确保动作要么全部完成，要么完全不起作用。

- 一致性（Consistency）：一旦事务完成（不管成功还是失败），系统必须确保它所建模的业务处于一致的状态，而不会是部分完成部分失败。
在现实中的数据不应该被破坏。

- 隔离性（Isolation）：可能有许多事务会同时处理相同的数据，因此每个事务都应该与其他事务隔离开来，防止数据损坏。

- 持久性（Durability）：一旦事务完成，无论发生什么系统错误，它的结果都不应该受到影响，这样就能从任何系统崩溃中恢复过来。
通常情况下，事务的结果被写到持久化存储器中。

#### 数据库的脏读、不可重复读和幻读

- 脏读（Dirty reads）：当事务A正在使用一个数据还未提交，此时事务B也在使用这个数据，一但事务A对这个数据的修改在之后回滚，则会导致事务B读取了
回滚前的脏数据。

- 不可重复读（Nonrepeatable read）：事务A读取了一条数据，然后执行逻辑的时候，事务B将这条数据改变了，然后事务A再次读取的时候，发现数据
不匹配了，就是所谓的不可重复读了。

- 幻读（Phantom read）：幻读与不可重复读类似。它发生在一个事务A读取了几行数据，接着另一个事务B插入了一些数据时。在随后的查询中，
第一个事务A就会发现多了一些原本不存在的记录。


#### Spring事务传播行为

- PROPAGATION_REQUIRED: 如果存在事务，则支持当前事务。如果没有事务，则单独开启一个事务。

- PROPAGATION_REQUIRED_NEW: 总是开启一个新的事务，如果已经有事务存在，则挂起这个事务。
														
- PROPAGATION_MONDATORY: 如果存在事务，则支持当前事务。如果没有事务，则会抛出一个异常。

- PROPAGETION_SUPPORTS: 如果存在事务，则支持当前事务。如果没有事务，则以非事务执行。

- PROPAGATION_NOT_SUPPORTED: 总是以非事务执行。如果已经有事务存在，则挂起这个事务。需要使用JtaTransactionManager。
															
- PROPAGATION_NERVER: 总是以非事务执行。如果已经有事务存在，则会抛出异常。

- PROPAGATION_NESTED: 如果存在事务，那么该方法将会在嵌套事务中运行。如果当前事务不存在，那么其行为与PROPAGATION_REQUIRED一样。
					这是一个嵌套事务,使用JDBC 3.0驱动时,仅仅支持DataSourceTransactionManager作为事务管理器。
					需要JDBC驱动的java.sql.Savepoint类。有一些JTA的事务管理器实现可能也提供了同样的功能。
					使用PROPAGATION_NESTED，还需要把PlatformTransactionManager的nestedTransactionAllowed属性设为true;
					而nestedTransactionAllowed属性值默认为false;
										
										
####  TransactionDefinition接口中定义五个隔离级别

- ISOLATION_DEFAULT：这是一个PlatfromTransactionManager默认的隔离级别，使用数据库默认的事务隔离级别.另外四个与JDBC的
					隔离级别相对应；

- ISOLATION_READ_UNCOMMITTED：这是事务最低的隔离级别，它充许别外一个事务可以看到这个事务未提交的数据。这种隔离级别会
							产生脏读，不可重复读和幻像读。

- ISOLATION_READ_COMMITTED：保证一个事务修改的数据提交后才能被另外一个事务读取。另外一个事务不能读取该事务未提交的数
							据。这种事务隔离级别可以避免脏读出现，但是可能会出现不可重复读和幻像读。

- ISOLATION_REPEATABLE_READ：这种事务隔离级别可以防止脏读，不可重复读。但是可能出现幻像读。它除了保证一个事务不能读
							取另一个事务未提交的数据外，还保证了避免下面的情况产生(不可重复读)。

- ISOLATION_SERIALIZABLE：这是花费最高代价但是最可靠的事务隔离级别。事务被处理为顺序执行。除了防止脏读，不可重复读外，
						还避免了幻读。		
						
						
#### 事务管理器 PlatformTransactionManager
Spring并不直接管理事务，而是提供了多种事务管理器，他们将事务管理的职责委托给Hibernate或者JTA等持久化机制所提供的相关平台框架的事务来实现。
PlatformTransactionManager接口定义了事务的传播行为、隔离级别、事务超时和只读状态等属性，同时还定义了getTransaction、commit、rollback
等方法。
 
常见的事务管理器的实现有DataSourceTransactionManager、HibernateTransactionManager、JpaTransactionManager、JtaTransactionManager。

- DataSourceTransactionManager：

- HibernateTransactionManager：

- JpaTransactionManager：

- JtaTransactionManager：它将事务管理的责任委托给javax.transaction.UserTransaction和javax.transaction.TransactionManager，
并且由UserTransaction对象来完成事务的提交和回滚。


### 编程式事务
Spring提供了对编程式事务和声明式事务的支持，编程式事务允许用户在代码中精确定义事务的边界，而声明式事务（基于AOP）有助于用户将操作与事务规则进行解耦。 
Spring提供两种方式的编程式事务管理，TransactionTemplate和直接使用PlatformTransactionManager。

#### TransactionTemplate