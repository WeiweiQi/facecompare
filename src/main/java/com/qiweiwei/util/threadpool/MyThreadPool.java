package com.qiweiwei.util.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.jfinal.kit.LogKit;

public class MyThreadPool {
	
	public static Executor getTheadPool(String threadName) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(
				1, 
				1000, 
				60L, 
				TimeUnit.SECONDS, 
				new LinkedBlockingQueue<Runnable>(100), new ThreadFactory() {
					@Override
					public Thread newThread(Runnable r) {
						Thread thread = new Thread(r);
						thread.setName(threadName);
						return thread;
					}
			
				}, 
				(r, e) -> LogKit.error("丢弃任务：runnable=" + r + ", exeception = " + e.toString()));
		return  executor;

	}
	
	public static void getThreadExecutor(Thread thread) {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		scheduledExecutorService.schedule(thread, 1, TimeUnit.SECONDS);
	}
	
}
