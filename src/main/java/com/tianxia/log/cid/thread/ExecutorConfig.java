package com.tianxia.log.cid.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: ExecutorConfig
 * @Description:
 * @Author: dream
 * @Date: 2024/6/26 16:24
 */
@Slf4j
@Configuration
public class ExecutorConfig {

    @Bean
    @Primary
    public Executor asyncServiceExecutor() {
        log.info("start asyncServiceExecutor");
        ThreadPoolExecutorMdcWrapper executor = new ThreadPoolExecutorMdcWrapper();
        //配置核心线程数
        executor.setCorePoolSize(100);
        //配置最大线程数
        executor.setMaxPoolSize(500);
        //配置队列大小
        executor.setQueueCapacity(10000);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-thread-pool-");
        // 设置拒绝策略：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }


}


