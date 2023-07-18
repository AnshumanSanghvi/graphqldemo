package com.anshuman.graphqldemo.config;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Configuration
public class ExecutorConfig {

    private static final int TOTAL_THREADS = 10;

    @Bean(name = "APIThreadExecutor")
    public Executor getExecutor() {
        ThreadFactory threadFactory = new BasicThreadFactory.Builder()
                .namingPattern("APIThread-%d")
                .daemon(false)
                .priority(Thread.NORM_PRIORITY)
                .wrappedFactory(Executors.defaultThreadFactory())
                .build();
        return Executors.newFixedThreadPool(TOTAL_THREADS, threadFactory);
    }
}
