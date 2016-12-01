package com.hszs.stb.common.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ExecutorPools {
	
	private static final Logger logger = LoggerFactory.getLogger(ExecutorPools.class);
	
	public static class ThreadPoolInfo implements Serializable{
		
		private static final long serialVersionUID = -3873878482513258858L;
		
		public int largestPoolSize;
		public int poolSize;
		public long completedTaskCount;
		public long taskCount;
		public int maximumPoolSize;
		public int corePoolSize;
		public int queuedSize;
		public int activeCount;
	}
	
	private static ExecutorPools _instance = new ExecutorPools();
	
	public static ExecutorPools getInstance(){
		return _instance;
	}
	
	private ExecutorPools(){
	}
	
	public final ExecutorService userinfo_push_executor = Executors.newFixedThreadPool(30);
	public final ExecutorService ppsvip_push_executor   = Executors.newFixedThreadPool(20);
	public final ExecutorService clear_cache_executor=  Executors.newFixedThreadPool(10);
	public final ExecutorService requestCounterExecutor =  Executors.newFixedThreadPool(10);
	public final ExecutorService reload_phpcache_executor = Executors.newFixedThreadPool(20);
	public final ExecutorService updateTokenThread=  Executors.newFixedThreadPool(10);
	public final ExecutorService qpsmapCounterExecutor =  Executors.newFixedThreadPool(10);
	public final ExecutorService noticeExecutor =  Executors.newFixedThreadPool(5);
	public final ExecutorService mqSubmitter=  Executors.newFixedThreadPool(30);
	public final ExecutorService log_executor = Executors.newFixedThreadPool(10);
	public final ExecutorService emailPool = Executors.newFixedThreadPool(5);
	public final ExecutorService friendPool = Executors.newFixedThreadPool(5);
	
	public Map<String, ThreadPoolInfo> getExecutorPoolsInfo(){
		Map<String, ThreadPoolInfo> map = new HashMap<String, ThreadPoolInfo>();
		map.put("userinfo_push", 	getExecutorPoolInfo(userinfo_push_executor));
		map.put("ppsvip_push", 		getExecutorPoolInfo(ppsvip_push_executor));
		map.put("clear_cache", 		getExecutorPoolInfo(clear_cache_executor));
		map.put("request_counter", 		getExecutorPoolInfo(requestCounterExecutor));
		map.put("reload_phpcache", 		getExecutorPoolInfo(reload_phpcache_executor));
		map.put("update_thirdparty_token", 	getExecutorPoolInfo(updateTokenThread));
		map.put("qps_counter", 			getExecutorPoolInfo(qpsmapCounterExecutor));
		map.put("notice_update", 		getExecutorPoolInfo(noticeExecutor));
		map.put("mq_submitter", 		getExecutorPoolInfo(mqSubmitter));
		map.put("kefu_log", 		getExecutorPoolInfo(log_executor));
		map.put("email_pool", 		getExecutorPoolInfo(emailPool));
		return map;
	}
	
	public ThreadPoolInfo getExecutorPoolInfo(ExecutorService executor){
		ThreadPoolInfo info = new ThreadPoolInfo();
		if(executor instanceof ThreadPoolExecutor){
			ThreadPoolExecutor threadPool = (ThreadPoolExecutor)executor;
			info.completedTaskCount = threadPool.getCompletedTaskCount();
			info.poolSize = threadPool.getPoolSize();
			info.taskCount = threadPool.getTaskCount();
			info.largestPoolSize = threadPool.getLargestPoolSize();
			info.maximumPoolSize = threadPool.getMaximumPoolSize();
			info.corePoolSize = threadPool.getCorePoolSize();
			info.queuedSize = threadPool.getQueue().size();
			info.activeCount = threadPool.getActiveCount();
		}else{
			logger.error("无法获取线程池信息，线程池{}不是ThreadPoolExecutor", executor);
		}
		return info;
	}
	
}
