package com.example.saf.common;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
    private ThreadPoolExecutor commonThreadPool = new ThreadPoolExecutor(0, Runtime.getRuntime().availableProcessors(), 1,
            TimeUnit.SECONDS, sPoolWorkQueue, defaultThreadFactory,
            new ThreadPoolExecutor.DiscardOldestPolicy());
    
    public static ThreadPoolManager getInstance(){
		return manager;
	}
    
    Future<T> future;
    public void addTask(Runnable runnable){
//    	commonThreadPool.execute(runnable);
    	future = commonThreadPool.submit(runnable);
	}
    
    public void shutdownNow(){
    	commonThreadPool.shutdownNow();
    }
    
    private void test() {
		
	}
}
