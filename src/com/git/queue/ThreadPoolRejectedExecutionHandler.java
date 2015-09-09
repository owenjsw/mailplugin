package com.git.queue;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;


public class ThreadPoolRejectedExecutionHandler implements RejectedExecutionHandler{
	private static final Logger logger = Logger.getLogger(ThreadPoolRejectedExecutionHandler.class);
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		// TODO Auto-generated method stub
		logger.debug("ThreadPool has error" );
	}

}
