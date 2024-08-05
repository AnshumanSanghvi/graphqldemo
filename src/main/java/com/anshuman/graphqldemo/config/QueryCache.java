package com.anshuman.graphqldemo.config;

import graphql.execution.preparsed.persisted.InMemoryPersistedQueryCache;
import graphql.execution.preparsed.persisted.PersistedQueryCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class QueryCache {

    @Bean
    public PersistedQueryCache persistedQueryCache() {
        return new InMemoryPersistedQueryCache(new HashMap<>(100));
    }
}
