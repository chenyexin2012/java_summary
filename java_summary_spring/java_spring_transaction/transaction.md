

#### Spring事务传播行为

- PROPAGATION_REQUIRED : 当前方法必须运行在事务中。如果当前事务存在，方法将会在该事务中运行。否则，会启动一个新的事务。

- PROPAGATION_REQUIRED_NEW : 表示当前方法必须运行在它自己的事务中。一个新的事务将被启动。如果存在当前事务，在该方法执
							行期间，当前事务会被挂起。如果使用JTATransactionManager的话，则需要访问TransactionManager。
														
- PROPAGATION_MONDATORY : 表示该方法必须在事务中运行，如果当前事务不存在，则会抛出一个异常。

- PROPAGETION_SUPPORTS : 表示当前方法不需要事务上下文，但是如果存在当前事务的话，那么该方法会在这个事务中运行。

- PROPAGATION_NOT_SUPPORTED : 表示该方法不应该运行在事务中。如果存在当前事务，在该方法运行期间，当前事务将被挂起。
							如果使用JTATransactionManager的话，则需要访问TransactionManager。
															
- PROPAGATION_NERVER : 表示当前方法不应该运行在事务上下文中。如果当前正有一个事务在运行，则会抛出异常。

- PROPAGATION_NESTED : 表示如果当前已经存在一个事务，那么该方法将会在嵌套事务中运行。嵌套的事务可以独立于当前事务
						进行单独地提交或回滚。如果当前事务不存在，那么其行为与PROPAGATION_REQUIRED一样。注意各厂商
						对这种传播行为的支持是有所差异的。可以参考资源管理器的文档来确认它们是否支持嵌套事务。
										
										
####  TransactionDefinition接口中定义五个隔离级别

- ISOLATION_DEFAULT 这是一个PlatfromTransactionManager默认的隔离级别，使用数据库默认的事务隔离级别.另外四个与JDBC的
					隔离级别相对应；

- ISOLATION_READ_UNCOMMITTED 这是事务最低的隔离级别，它充许别外一个事务可以看到这个事务未提交的数据。这种隔离级别会
									产生脏读，不可重复读和幻像读。

- ISOLATION_READ_COMMITTED  保证一个事务修改的数据提交后才能被另外一个事务读取。另外一个事务不能读取该事务未提交的数
							据。这种事务隔离级别可以避免脏读出现，但是可能会出现不可重复读和幻像读。

- ISOLATION_REPEATABLE_READ  这种事务隔离级别可以防止脏读，不可重复读。但是可能出现幻像读。它除了保证一个事务不能读
							取另一个事务未提交的数据外，还保证了避免下面的情况产生(不可重复读)。

- ISOLATION_SERIALIZABLE 这是花费最高代价但是最可靠的事务隔离级别。事务被处理为顺序执行。除了防止脏读，不可重复读外，
						还避免了幻像读。		