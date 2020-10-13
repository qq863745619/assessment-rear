package com.nju.software.assessment.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPool {
    private volatile static ExecutorService cachedThreadPool = null;
    private MyThreadPool(){

    }

    public static ExecutorService getInstance(){
        if(cachedThreadPool==null){
            synchronized (Executors.class){
                if(cachedThreadPool==null){
                    cachedThreadPool = Executors.newCachedThreadPool();
                }
            }
        }
        return cachedThreadPool;
    }

}
