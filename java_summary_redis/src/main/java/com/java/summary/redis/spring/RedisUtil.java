package com.java.summary.redis.spring;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * RedisUtil
 */
public class RedisUtil {

    private RedisTemplate<String, Object> redisTemplate;

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 设置指定缓存的失效时间
     *
     * @param key  键  not null
     * @param time 时间(毫秒)
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取对应key的失效时间
     *
     * @param key 键 not null
     * @return 时间(毫秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键 not null
     * @return true 存在 false 不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key not null 一个值或多个值
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    // ============================String=============================

    /**
     * 获取key对应的值
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 添加值
     *
     * @param key   not null 键
     * @param value 值
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 添加值并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(毫秒) 小于0则不设置过期时间
     * @return
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.MILLISECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     *
     * @param key 键
     * @param delta  增加值(大于0)
     * @return
     */
    public long increment(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("delta必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key 键
     * @param delta  减少值(大于0)
     * @return
     */
    public long decrement(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("delta必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }


    /**********************************hash start*******************************************/

    /**
     * 返回名称为key的hash中field对应的value
     *
     * @param key  键 不能为null
     * @param hashKey 项 不能为null
     * @return 值
     */
    public Object hget(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取key对应的hash键值对
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 向名称为key的hash中添加键值对
     *
     * @param key 键
     * @param map 对应多个键值对
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向名称为key的hash中添加一对键值
     *
     * @param key   键
     * @param hashKey  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key, String hashKey, Object value) {
        try {
            redisTemplate.opsForHash().put(key, hashKey, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中对应项的值
     *
     * @param key  键 不能为null
     * @param hashKey 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    /**
     * 判断hash表中对应项的是否存在
     *
     * @param key  键 不能为null
     * @param hashKey 项 不能为null
     * @return true 存在 false 不存在
     */
    public boolean hHasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * hash表中对应项的值递增，如果不存在,就会创建一个，并把新增后的值返回
     *
     * @param key  键
     * @param hashKey 项
     * @param delta   增加值(大于0)
     * @return
     */
    public double hIncrement(String key, String hashKey, double delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    /**
     * hash表中对应项的值递减
     *
     * @param key  键
     * @param hashKey 项
     * @param delta   减小值(小于0)
     * @return
     */
    public double hDecrement(String key, String hashKey, double delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, -delta);
    }

    /**********************************hash end*******************************************/


    /**********************************set start*******************************************/
    /**
     * 根据key获取对应的set
     *
     * @param key 键
     * @return
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断key对应的set中是否存在value
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false 不存在
     */
    public boolean sHasValue(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放入key对应的set中
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 添加成功数
     */
    public long sAdd(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * key对应的set的长度
     *
     * @param key 键
     * @return
     */
    public long sSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除key对应的set中的对应值
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**********************************set end*******************************************/

    /**********************************list start*******************************************/
    /**
     * 获取key对应的list
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return
     */
    public List<Object> lRange(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取key对应的list的长度
     *
     * @param key 键
     * @return
     */
    public long lSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引获取key对应的list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时 从下标开始；index<0时，-1表示表尾，-2表示倒数第二个元素，依次类推
     * @return
     */
    public Object lGet(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 在名称为key的list尾添加一个值为value的元素
     *
     * @param key   键
     * @param value 值
     * @return 成功 1 失败 0
     */
    public long lRpush(String key, Object value) {
        try {
            return redisTemplate.opsForList().rightPush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 在名称为key的list头添加一个值为value的元素
     * @param key
     * @param value
     * @return  成功 1 失败 0
     */
    public long lLpush(String key, Object value) {

        try {
            return redisTemplate.opsForList().leftPush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 在名称为key的list尾添加一个list
     *
     * @param key   键
     * @param values list
     * @return 添加成功数
     */
    public long lRpushAll(String key, List<Object> values) {
        try {
            return redisTemplate.opsForList().rightPushAll(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 在名称为key的list头添加一个list
     *
     * @param key   键
     * @param values list
     * @return 添加成功数
     */
    public long lLpushAll(String key, List<Object> values) {
        try {
            return redisTemplate.opsForList().leftPushAll(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 根据索引修改名称为key的list中的数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lSet(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除名称为key的list中N个值为value的项
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**********************************list end*******************************************/
}
