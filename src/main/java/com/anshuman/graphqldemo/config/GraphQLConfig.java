package com.anshuman.graphqldemo.config;

import graphql.execution.preparsed.PreparsedDocumentProvider;
import graphql.execution.preparsed.persisted.ApolloPersistedQuerySupport;
import graphql.execution.preparsed.persisted.InMemoryPersistedQueryCache;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration(proxyBeanMethods = false)
public class GraphQLConfig {

    private static final Map<Object, String> cacheMap = new ConcurrentHashMap<>(100);

    @Bean
    public InMemoryPersistedQueryCache inMemoryPersistedQueryCache() {
        return new InMemoryPersistedQueryCache(cacheMap);
    }

    @Bean
    GraphQlSourceBuilderCustomizer sourceBuilderCustomizer(ObjectProvider<DataFetcherExceptionResolver> resolvers,
                                                           InMemoryPersistedQueryCache inMemoryPersistedQueryCache) {
        // Create provider
        PreparsedDocumentProvider provider = new ApolloPersistedQuerySupport(inMemoryPersistedQueryCache);
        return sourceBuilder -> sourceBuilder.configureGraphQl(builder ->
                builder.preparsedDocumentProvider(provider));
    }
}
