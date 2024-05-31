package com.anshuman.graphqldemo.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.data.method.annotation.support.AnnotatedControllerConfigurer;

import java.util.concurrent.Executor;

@Configuration
public class ControllerConcurrencyConfig {

    @Bean
    public AnnotatedControllerConfigurer annotatedControllerConfigurer(@Qualifier("APIThreadExecutor") Executor executor) {
        AnnotatedControllerConfigurer configurer = new AnnotatedControllerConfigurer();
        configurer.setExecutor(executor);
        return configurer;
    }
}
