package com.java.summary.redis;

import com.java.summary.redis.utils.JedisPoolUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class JedisTest {

    Jedis jedis = null;

    @Before
    public void before() {
        jedis = JedisPoolUtil.getJedis();
    }

    @After
    public void after() {
        jedis.flushAll();
    }

    @Test
    public void test() {

        System.out.println(jedis.del("111"));
    }

    @Test
    public void testJedisPoolUtil() {
        System.out.println(JedisPoolUtil.getJedis());
    }

    @Test
    public void testKeyValue() {

        // 添加成功返回OK
        System.out.println(jedis.set("name", "Holmes"));
        // setnx，key不存在则添加并返回1，key存在则添加失败并返回0
        System.out.println(jedis.setnx("age", "18"));
        // 设置超时时间
        System.out.println(jedis.expire("age", 10));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 超时后可以重新添加
        System.out.println(jedis.setnx("age", "19"));

        System.out.println(jedis.get("name"));
        System.out.println(jedis.get("age"));

        System.out.println(jedis.del("name"));
        System.out.println(jedis.del("age"));
        System.out.println(jedis.del("age"));

        System.out.println(jedis.get("name"));
        System.out.println(jedis.get("age"));
    }

    @Test
    public void testList() {
        // 尾部添加 返回当前长度
        System.out.println(jedis.rpush("testList", "a", "b", "c"));
        System.out.println(jedis.rpush("testList", "a"));

        // 头部添加 返回当前长度
        System.out.println(jedis.lpush("testList", "x", "y"));

        System.out.println("testList's length = " + jedis.llen("testList"));

        // 返回list中start至end之间的元素，下标从0开始
        List<String> list = jedis.lrange("testList", 0, 5);
        System.out.println(list);

        // 截取list中start至end之间的元素，下标从0开始，成功返回OK
        System.out.println(jedis.ltrim("testList", 0, 4));
        list = jedis.lrange("testList", 0, jedis.llen("testList") - 1);
        System.out.println(list);

        // 为下标为index的元素赋值
        System.out.println(jedis.lset("testList", 2, "sfs"));
        list = jedis.lrange("testList", 0, jedis.llen("testList") - 1);
        System.out.println(list);

        // 删除count个名称为key的list中值为value的元素。count为0，删除所有值为value的元素，
        // count>0，从头至尾删除count个值为value的元素，count<0从尾到头删除|count|个值为value的元素。
        System.out.println(jedis.lrem("testList", 0, "sfs"));
        list = jedis.lrange("testList", 0, jedis.llen("testList") - 1);
        System.out.println(list);

    }

    @Test
    public void testHashMap() {

        // 添加
        System.out.println(jedis.hset("testHashMap", "name", "holmes"));
        System.out.println(jedis.hset("testHashMap", "age", "18"));

        System.out.println(jedis.hget("testHashMap", "name"));
        System.out.println(jedis.hget("testHashMap", "age"));
        System.out.println(jedis.hget("testHashMap", "aaa"));
        System.out.println(jedis.hget("noHashMap", "aaa"));

        // 删除 返回删除数量
        System.out.println(jedis.hdel("testHashMap", "aaa"));
        System.out.println(jedis.hdel("testHashMap", "name"));
        System.out.println(jedis.hdel("testHashMap", "age"));

        System.out.println(jedis.hget("testHashMap", "name"));
        System.out.println(jedis.hget("testHashMap", "age"));
    }

    @Test
    public void testSet() {

        // 返回添加成功的个数
        System.out.println(jedis.sadd("testSet", "a", "b", "d", "c", "g", "f"));
        System.out.println(jedis.sadd("testSet", "h", "w", "j", "k", "g", "f"));

        // 获取集合的长度
        System.out.println("testSet's length = " + jedis.scard("testSet"));
        // 获取集合
        Set<String> set = jedis.smembers("testSet");
        System.out.println(set);

        // 删除集合中的元素 返回成功个数
        System.out.println(jedis.srem("testSet", "a", "b", "x"));
        System.out.println(jedis.srem("testSet", "z"));
        System.out.println("testSet's length = " + jedis.scard("testSet"));

        showLine();
        jedis.sadd("testSet2", "a", "b", "x", "v", "h", "f");
        // 交集 返回集合
        System.out.println(jedis.sinter("testSet", "testSet2"));
        // 求交集并保存 返回集合长度
        System.out.println(jedis.sinterstore("inter", "testSet", "testSet2"));
        System.out.println(jedis.smembers("inter"));

        showLine();
        // 并集 返回集合
        System.out.println(jedis.sunion("testSet", "testSet2"));
        // 求并集并保存 返回集合长度
        System.out.println(jedis.sunionstore("union", "testSet", "testSet2"));
        System.out.println(jedis.smembers("union"));

        showLine();
        // 差集 返回集合
        System.out.println(jedis.sdiff("testSet", "testSet2"));
        // 求差集保存 返回集合长度
        System.out.println(jedis.sdiffstore("diff", "testSet", "testSet2"));
        System.out.println(jedis.smembers("diff"));
    }

    @Test
    public void sortedSet() {

        System.out.println(jedis.zadd("sortedSet", 1, "value1"));
        System.out.println(jedis.zadd("sortedSet", 2, "value2"));
        System.out.println(jedis.zadd("sortedSet", 4, "value3"));
        System.out.println(jedis.zadd("sortedSet", 3, "value4"));

        showLine();
        // 按score升序规则，返回对应member的下标index（0开始），不存在返回null
        System.out.println(jedis.zrank("sortedSet", "value4"));
        System.out.println(jedis.zrank("sortedSet", "value10"));

        // 按score降序序规则，返回对应member的下标index（0开始），不存在返回null
        System.out.println(jedis.zrevrank("sortedSet", "value4"));
        System.out.println(jedis.zrevrank("sortedSet", "value10"));

        showLine();

        // 按score升序规则，返回下标从start到end的所有元素集合
        Set set = jedis.zrange("sortedSet", 1, 3);
        System.out.println(set);

        // 按score降序规则，返回下标从start到end的所有元素集合
        set = jedis.zrevrange("sortedSet", 1, 3);
        System.out.println(set);

        showLine();

        // 返回score在min和max之间的元素集合
        set = jedis.zrangeByScore("sortedSet", 1, 3);
        System.out.println(set);


    }

    private void showLine() {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }


}
