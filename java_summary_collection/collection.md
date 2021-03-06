## 集合类
    
    java中的的集合类主要实现自接口Collection、Map。
    
    1.Collection：List、Set、Queue以及它们的实现类。
    
    2.Map：HashMap、HashTable以及其它实现类。
    

### List

List接口继承自Collection，常用的实现类有：ArrayList、LinkedList、Vector
    
1.ArrayList

- 以数组作为存储结构（长度不够，动态扩容，长度为之前的1.5倍）
- 访问成员速度较快，插入删除对象速度较慢
- 线程不安全
	
2.LinkedList

- 以链表作为存储结构
- 能够快速执行插入删除操作，但访问速度较慢
- 线程不安全
	
3.Vector	

- 以数组作为存储结构
- 线程安全
- 效率低，一般不用


### Set （成员不能重复）

Set接口继承自Collection，表示数学中的集合，常用的实现类有：HashSet、TreeSet、LinkedHashSet

1.HashSet 

- 不能保证元素的排列顺序，顺序有可能发生变化
- 线程不安全
- 成员可以为任何Object子类的对象，同时需要覆盖eqauls和hashCode方法，否则无法判断成员是否相等
- 成员可以为null，且只有一个成员可以为null


    注：hashSet通过equals方法来判断两个成员是否相等，同时两个对象返回的hashCode值也应该相等。因此在重写成员对象的对应类
    的equals方法时也应当使用同种规则重写hashCode方法。
			
2.TreeSet

- 元素遍历是有序
- 成员不能为null
- 线程不安全


    注：TreeSet通过equals判断成员是否相等的情况下还要判断CompareTo返回值是否为0，因此hashSet中成员对象的对应类必须实现
        Comparable接口中的compareTo方法。
    实现排序方法分为内部排序和外部排序：
        内部排序是指对象对应类的内部实现Comparable接口中的compareTo方法。
        外部排序是指通过比较器Comparator来实现排序规则，使用外部排序可以在不改变对象自身的情况下改变排序策略对对象进行排序。
        
    注：当两种方法都实现的情况下，优先使用外部排序规则。
	
3.LinkedHashSet

- 遍历顺序和插入顺序是一致
- 与HashSet类似


### Map

1.HashMap、Hashtable与ConcurrentHashMap

    利用哈希表作为键值存储
    HashMap的键与值都可以为null，后两者不可以
    HashMap非线程安全，后两者线程安全，且ConcurrentHashMap使用了分段锁
		
2.遍历方法

    a.通过map.keySet()获取所有key遍历所有键值对，二次取值，效率低
        Iterator<Long> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Long key = iterator.next();
            System.out.println(key + " = " + map.get(key));
        }
        
    b.通过map.entrySet()获取entrySet通过Iterator遍历，效率较高
        Iterator<Entry<Long, String>> iterator2 = map.entrySet().iterator();
        while (iterator2.hasNext()) {
            Entry<Long, String> entry = iterator2.next();
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    
    c.遍历ap.entrySet()获取键值对
        Set<Entry<Long, String>> entry = map.entrySet();
        for (Entry<Long, String> entry2 : entry) {
            System.out.println(entry2.getKey() + " = " + entry2.getValue());
        }



### Queue

Queue接口继承自Collection，常用的实现类有：
    
    java.util.PriorityQueue
    java.util.concurrent.LinkedBlockingQueue
    java.util.concurrent.ArrayBlockingQueue
    java.util.concurrent.PriorityBlockingQueue

1.阻塞队列与非阻塞队列

2.Queue与Deque的区别
    
3.一些方法的区别：
    
|方法|功能|备注|
|:----|:----|----|
|add|增加一个元素|如果队列已满，则抛出一个IllegalStateException异常|
|remove|移除并返回队列头部的元素|如果队列为空，则抛出一个NoSuchElementException异常|
|element|返回队列头部的元素|如果队列为空，则抛出一个NoSuchElementException异常|
|offer|添加一个元素并返回true|如果队列已满，则返回false|
|poll|移除并返问队列头部的元素|如果队列为空，则返回null|
|peek|返回队列头部的元素|如果队列为空，则返回null|
|put|添加一个元素|如果队列满，则阻塞|
|take|移除并返回队列头部的元素|如果队列为空，则阻塞|


### Collections	

提供了集合操作的工具类。
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		