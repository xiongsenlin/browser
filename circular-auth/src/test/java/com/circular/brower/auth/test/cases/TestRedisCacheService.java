package com.circular.brower.auth.test.cases;

import com.circular.brower.auth.test.base.TestBase;
import com.circular.browser.auth.dao.model.User;
import com.circular.browser.auth.service.cache.CacheItem;
import com.circular.browser.auth.service.cache.CacheService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by senlin.xsl on 2015/4/16.
 */
public class TestRedisCacheService extends TestBase {
    @Autowired
    private CacheService cacheService;

    @Test
    public void testSet() {
        CacheItem<String> cacheItem = new CacheItem<String>();
        cacheItem.setKey("test");
        cacheItem.setValue("value");
        cacheItem.setExpireTime(200);

        this.cacheService.set(cacheItem);

        User user = new User();
        user.setCreateTime(new Date());

        CacheItem<User> cacheItem1 = new CacheItem<User>();
        cacheItem1.setExpireTime(null);
        cacheItem1.setKey("user");
        cacheItem1.setValue(user);
        this.cacheService.set(cacheItem1);

    }

    @Test
    public void testGet() {
        String value = this.cacheService.get("test", String.class);
        System.out.println(value);

        User user = this.cacheService.get("user", User.class);
    }

    @Test
    public void testDelete() {
        this.cacheService.delete("test");
        this.cacheService.delete("user".getBytes());
    }
}
