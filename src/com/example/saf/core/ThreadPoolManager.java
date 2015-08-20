/** 
 * File Name:ThreadPoolManager.java
 * 
 * Version:1.0
 *
 * Date:2015-8-20
 *
 * Description:
 *
 * Author:erik
 * Email:erik7@126.com
 * Copyright (c) 2015 yxtek Information Co.,Ltd.
 * All Rights Reserved. 
 */ 


package com.example.saf.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


import android.util.Log;

public class ThreadPoolManager {
	private static final ThreadPoolManager manager= new ThreadPoolManager();
		
	private final static String TAG = "yxtek-common-threadpool";

    private ThreadFactory defaultThreadFactory = new ThreadFactory() {
		private final AtomicInteger mCount = new AtomicInteger(1);
        public Thread newThread(Runnable r) {
        	Thread thread = new Thread(r, TAG+" #" + mCount.getAndIncrement());
        	thread.setPriority(3);
            return thread;
        }
	};
	private final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<Runnable>(20);
	
	private int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
	private int MAXIMUM_POOL_SIZE = CORE_POOL_SIZE * 2;
	
    private ThreadPoolExecutor commonThreadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 1,
            TimeUnit.SECONDS, sPoolWorkQueue, defaultThreadFactory,
            new ThreadPoolExecutor.DiscardOldestPolicy());
    
    public static ThreadPoolManager getInstance(){
		return manager;
	}
    
    Future<String> future;
    public void addTask(Runnable runnable){
    	commonThreadPool.execute(runnable);
	}
    
    
    public void addHttpTask(HttpTask task) {
		tasks.add(task);
		future = commonThreadPool.submit(task, "common");
	}
    
    List<HttpTask> tasks =new ArrayList<HttpTask>();
    
    public void stopAllTasks(){
//    	commonThreadPool.shutdownNow();
    	synchronized(HttpTask.class){
	    	if(null!=future){
	    		future.cancel(true);
	    	}
	    	
	    	if(!tasks.isEmpty()){
	    		for(HttpTask r:tasks){
	    			r.cancel();
	    			Log.i("", "cancel myhttptask");
	    			commonThreadPool.remove(r);
	    		}
	    	}
	    	tasks.clear();
    	}
    }
    
    
}
