package com.java.summary.redis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisPoolUtil {

    private static JedisPool jedisPool = null;

    private static ThreadLocal<Jedis> localJedis = new ThreadLocal<Jedis>();

    static {

        InputStream inputStream = JedisPoolUtil.class.getClassLoader().getResourceAsStream("config/redis.properties");

        Properties properties = new Properties();

        try {
            properties.load(inputStream);

            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Integer.valueOf(properties.get("redis.maxTotal").toString()));
            config.setMaxIdle(Integer.valueOf(properties.get("redis.maxIdle").toString()));
            config.setMaxWaitMillis(Long.valueOf(properties.get("redis.maxWaitMillis").toString()));

            jedisPool = new JedisPool(config, properties.get("redis.host").toString(),
                    Integer.valueOf(properties.get("redis.port").toString()), 10000,
                    properties.get("redis.password").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Jedis getJedis() {

        Jedis jedis = localJedis.get();
        if (null == jedis) {
            jedis = jedisPool.getResource();
            localJedis.set(jedis);
        }
        return jedis;
    }

    public static void closeJedis() {
        Jedis jedis = localJedis.get();
        if (null != jedis) {
            jedis.close();
        }
        localJedis.set(null);
    }
}
