package com.circular.browser.common.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by senlin.xsl on 2015/3/17.
 */
public class ThreadPoolManager {
    private static ThreadPoolManager instance;

    private ExecutorService cachedThreadPool;
    private ExecutorService singleThreadPool;

    private static Object synObj = new Object();

    private ThreadPoolManager() {}

    public static ThreadPoolManager getInstance() {
        if(instance != null)
            return instance;
        else {
            synchronized (synObj){
                if(instance == null) {
                    instance = new ThreadPoolManager();
                }
            }
            return instance;
        }
    }

    public synchronized ExecutorService getCachedThreadPool() {
        if(this.cachedThreadPool == null || this.cachedThreadPool.isShutdown())
            this.cachedThreadPool = Executors.newCachedThreadPool();
        return this.cachedThreadPool;
    }

    public synchronized ExecutorService getSingleThreadPool() {
        if(this.singleThreadPool == null || this.singleThreadPool.isShutdown())
            this.singleThreadPool = Executors.newSingleThreadExecutor();
        return this.singleThreadPool;
    }
}
