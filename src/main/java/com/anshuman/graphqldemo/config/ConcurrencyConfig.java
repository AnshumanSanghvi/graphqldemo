package com.anshuman.graphqldemo.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.data.method.annotation.support.AnnotatedControllerConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync
public class ConcurrencyConfig {

    @Bean(name = "APIThreadExecutor")
    public Executor getExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    @Bean
    public AnnotatedControllerConfigurer annotatedControllerConfigurer(@Qualifier("APIThreadExecutor") Executor executor) {
        AnnotatedControllerConfigurer configurer = new AnnotatedControllerConfigurer();
        configurer.setExecutor(executor);
        return configurer;
    }
}
