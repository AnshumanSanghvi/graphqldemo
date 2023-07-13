package com.anshuman.graphqldemo.config;

import com.anshuman.graphqldemo.resource.filter.GraphQLFilter;
import graphql.execution.preparsed.persisted.PersistedQueryCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class FilterConfig {

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeClientInfo(true);
        filter.setIncludeHeaders(true);
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setBeforeMessagePrefix("||");
        filter.setBeforeMessageSuffix("||");
        filter.setAfterMessagePrefix("||");
        filter.setAfterMessageSuffix("||");
        return filter;
    }

    @Bean
    public GraphQLFilter graphQLFilter(PersistedQueryCache persistedQueryCache) {
        return new GraphQLFilter(persistedQueryCache);
    }
}
