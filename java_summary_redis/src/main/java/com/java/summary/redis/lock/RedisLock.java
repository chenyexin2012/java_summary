package com.java.summary.redis.lock;

import com.java.summary.redis.utils.JedisPoolUtil;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class RedisLock implements Lock {

    private String name;

    private int expire;

    private long waitInterval;

    /**
     * @param name         锁的标识名，不可为空
     * @param expire       锁的生存时间，单位秒，及redis中key的生存时间
     * @param waitInterval 获取锁失败后挂起再试的时间间隔，单位毫秒
     */
    public RedisLock(String name, int expire, long waitInterval) throws RedisLockException {
        if (null == name || "".equals(name)) {
            throw new RedisLockException("锁的标识名不可为空！");
        }
        this.name = name;
        this.expire = expire;
        this.waitInterval = waitInterval;
    }

    @Override
    public void lock() {

        Jedis jedis = JedisPoolUtil.getJedis();

        while (true) {

            // 尝试向redis中插入锁的标识名
            long res = jedis.setnx(name, "");
            if (res == 1L) {
                // 加入成功，为锁的标识添加超时时间
                jedis.expire(name, expire);
                break;
            }

            // 获取key的剩余生存时间
            long ttl = jedis.ttl(name);
            //ttl小于0 表示key上没有设置生存时间（key是不会不存在的，因为前面setnx会自动创建）
            //如果出现这种状况，那就是进程的某个实例setnx成功后 crash 导致紧跟着的expire没有被调用
            //这时可以直接设置expire并把锁纳为己用
            if (ttl < 0L) {
                // 为锁的标识重新设置超时时间
                jedis.expire(name, expire);
                break;
            }

            try {
                // 等待下次重新获取锁
                Thread.sleep(waitInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        Jedis jedis = JedisPoolUtil.getJedis();
        jedis.del(name);
        JedisPoolUtil.closeJedis();
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
