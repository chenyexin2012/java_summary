package com.java.summary.redis.lock;

import com.java.summary.redis.utils.JedisPoolUtil;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class RedisLock implements Lock {

    /**
     * 锁标识名的统一后缀
     */
    private final static String SUFFIX = "_lock";
    /**
     * 锁的标识名，不可为空
     */
    private String name;

    /**
     * 锁的生存时间，单位毫秒，及redis中key的生存时间
     */
    private long expire = 60 * 1000L;

    /**
     * 获取锁失败后挂起再试的时间间隔，单位毫秒
     */
    private long waitInterval = 100L;

    /**
     * @param name
     * @throws RedisLockException
     */
    public RedisLock(String name) throws RedisLockException {
        if (null == name || "".equals(name)) {
            throw new RedisLockException("锁的标识名不可为空！");
        }
        this.name = name + SUFFIX;
    }
    /**
     * @param name
     * @param expire
     * @param waitInterval
     */
    public RedisLock(String name, long expire, long waitInterval) throws RedisLockException {
        if (null == name || "".equals(name)) {
            throw new RedisLockException("锁的标识名不可为空！");
        }
        this.name = name + SUFFIX;
        this.expire = expire;
        this.waitInterval = waitInterval;
    }

    @Override
    public void lock() {

        Jedis jedis = JedisPoolUtil.getJedis();

        while (true) {

            long expireTime = this.expire + System.currentTimeMillis();
            String expireStr = String.valueOf(expireTime);
            // 尝试向redis中插入锁的标识名
            long res = jedis.setnx(name, expireStr);
            if (res == 1L) {
                break;
            }
            // 锁的标识名存在，则判断其是否超时
            String currentExpireStr = jedis.get(name);
            if(null != currentExpireStr && Long.valueOf(currentExpireStr) < System.currentTimeMillis()){
                // 此时则表示锁已经超时
                // 向锁中添加新的值，并获取之前的值与currentExpireStr作比较，防止其它线程修改了存储的值
                // 此时虽然可能在没有成功获取锁的请况下更新了值，但由于误差较小，可以忽略
                String oldExpireStr = jedis.getSet(name, expireStr);
                // 值相等则表示没有其他线程获取了锁
                if(currentExpireStr.equals(oldExpireStr)) {
                    break;
                }
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

        Jedis jedis = JedisPoolUtil.getJedis();

        long expireTime = this.expire + System.currentTimeMillis();
        String expireStr = String.valueOf(expireTime);
        // 尝试向redis中插入锁的标识名
        long res = jedis.setnx(name, expireStr);
        if (res == 1L) {
            return true;
        }

        // 锁的标识名存在，则判断其是否超时
        String currentExpireStr = jedis.get(name);
        if(null != currentExpireStr && Long.valueOf(currentExpireStr) < System.currentTimeMillis()){
            // 此时则表示锁已经超时
            // 向锁中添加新的值，并获取之前的值与currentExpireStr作比较，防止其它线程修改了存储的值
            // 此时虽然可能在没有成功获取锁的请况下更新了值，但由于误差较小，可以忽略
            String oldExpireStr = jedis.getSet(name, expireStr);
            // 值相等则表示没有其他线程获取了锁
            if(currentExpireStr.equals(oldExpireStr)) {
                return true;
            }
        }

        JedisPoolUtil.closeJedis();
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {

        Jedis jedis = JedisPoolUtil.getJedis();

        long timeoutT =unit.toMillis(time) + System.currentTimeMillis();
        while (timeoutT > System.currentTimeMillis()) {

            long expireTime = this.expire + System.currentTimeMillis();
            String expireStr = String.valueOf(expireTime);
            // 尝试向redis中插入锁的标识名
            long res = jedis.setnx(name, expireStr);
            if (res == 1L) {
                return true;
            }

            // 锁的标识名存在，则判断其是否超时
            String currentExpireStr = jedis.get(name);
            if(null != currentExpireStr && Long.valueOf(currentExpireStr) < System.currentTimeMillis()){
                // 此时则表示锁已经超时
                // 向锁中添加新的值，并获取之前的值与currentExpireStr作比较，防止其它线程修改了存储的值
                // 此时虽然可能在没有成功获取锁的请况下更新了值，但由于误差较小，可以忽略
                String oldExpireStr = jedis.getSet(name, expireStr);
                // 值相等则表示没有其他线程获取了锁
                if(currentExpireStr.equals(oldExpireStr)) {
                    return true;
                }
            }
            try {
                // 等待下次重新获取锁
                Thread.sleep(waitInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        JedisPoolUtil.closeJedis();
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
