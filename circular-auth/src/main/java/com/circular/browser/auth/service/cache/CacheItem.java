package com.circular.browser.auth.service.cache;

/**
 * Created by senlin.xsl on 2015/4/16.
 */
public class CacheItem <T> {
    private String key;

    /**
     * 如果是String 类型，则直接放到缓存中，否则序列化后放入缓存中
     */
    private T value;

    /**
     * 数据超时时间，如果为null，表示永远有效，单位：秒
     */
    private Integer expireTime;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Integer getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }
}
