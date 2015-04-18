package com.circular.browser.auth.service.cache;

import com.circular.brower.common.util.SerializeUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by senlin.xsl on 2015/4/16.
 */
@Service("cacheService")
public class CacheService {

    private Logger logger = LogManager.getLogger(CacheService.class);

    @Autowired
    private JedisPool jedisPool;

    /**
     * 将数据添加到缓存中，如果对象是String，则直接添加到缓存，否则序列化后保存到缓存中
     * @param cacheItem
     * @param <T>
     * @return
     */
    public <T> boolean set(CacheItem<T> cacheItem) {
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();

            String key = cacheItem.getKey();
            T value = cacheItem.getValue();
            Integer expireTime = cacheItem.getExpireTime();

            if (value instanceof String) {
                if (expireTime == null) {
                    jedis.set(key, (String) value);
                }
                else {
                    jedis.setex(key, expireTime, (String) value);
                }
            }
            else {
                if (expireTime == null) {
                    jedis.set(key.getBytes(), SerializeUtil.serialize(value));
                }
                else {
                    jedis.setex(key.getBytes(), expireTime, SerializeUtil.serialize(value));
                }
            }
            return true;
        }
        catch (Exception e) {
            logger.error("add data to cache error", e);
            return false;
        }
        finally {
            if (jedis != null) {
                this.jedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 从缓存中获取对象
     * @param key
     * @param resultType
     * @param <T>
     * @return
     */
    public <T> T get(String key, Class<T> resultType) {
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();

            if (resultType == String.class) {
                return (T) jedis.get(key);
            }
            else {
                byte [] bytes = jedis.get(key.getBytes());
                return (T) SerializeUtil.unserialize(bytes);
            }
        }
        catch (Exception e) {
            logger.error("get data from cache error", e);
        }
        finally {
            if (jedis != null) {
                this.jedisPool.returnResource(jedis);
            }
        }
        return null;
    }

    /**
     * 将数据从缓存中清除
     * @param key
     * @param <T>
     * @return
     */
    public <T> boolean delete(T key) {
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();

            if (key instanceof String) {
                jedis.del((String) key);
            }
            else {
                jedis.del((byte []) key);
            }
            return true;
        }
        catch (Exception e) {
            logger.error("delete data from cache error", e);
            return false;
        }
        finally {
            if (jedis != null) {
                this.jedisPool.returnResource(jedis);
            }
        }
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
