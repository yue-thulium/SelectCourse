package com.hairgroup.choose.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class ExecutorConfig {
	private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);
	
	private ExecutorConfig() {}

	/**
	 * 线程池最大核心数
	 */
//    private int corePoolSize = Runtime.getRuntime().availableProcessors();
    private static int corePoolSize = 5;
    
    /**
     * 线程池最大数目
     */
    private static int maxPoolSize = Integer.MAX_VALUE;
    
    /**
     * 存活时间
     */
    private static Long keepAliveTime = 60L;
    
    /**
     * 阻塞队列
     */
    private static SynchronousQueue synchronousQueue = new SynchronousQueue<>();
    
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("datas-thread-%d").build();

    public static ExecutorService createThreadPool() {
        logger.info("线程池开始创建");
        ExecutorService threadPool = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, synchronousQueue, namedThreadFactory);
        logger.info("线程池创建结束");
        return threadPool;
    }
}
