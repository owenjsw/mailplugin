package com.git.queue;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.git.util.LoadProperties;
import org.apache.log4j.Logger;

public  class ThreadPoolManager  {
	private static ThreadPoolExecutor pool = null;
	private static final Logger logger = Logger.getLogger(ThreadPoolManager.class);
	public static void init(){
		int cpus = Runtime.getRuntime().availableProcessors();
		int scaleFactor = Integer.parseInt(LoadProperties.getProperty("scaleFactor"));
		int maxThreads = cpus * scaleFactor;
		maxThreads = (maxThreads > 0 ? maxThreads : 1);
		pool = new ThreadPoolExecutor(0, maxThreads,  
	            60L, TimeUnit.MINUTES, new SynchronousQueue<Runnable>());
	}
	
	 public void destory() {  
	        if(pool != null) {  
	            pool.shutdownNow();  
	        }  
	    }  
	 public static ExecutorService getThreadPoolManager() {  
	        return pool;  
	    }  
	public static void shutdown(){
		logger.debug("ThreadPool shutdown but there in Queue :"+pool.getQueue());
		pool.shutdownNow();
	}
}
