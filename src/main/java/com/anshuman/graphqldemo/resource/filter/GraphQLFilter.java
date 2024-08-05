package com.anshuman.graphqldemo.resource.filter;

import graphql.execution.preparsed.persisted.InMemoryPersistedQueryCache;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

import static com.anshuman.graphqldemo.util.StringUtil.truncate;
import static java.util.stream.Collectors.joining;

@Slf4j
@RequiredArgsConstructor
public class GraphQLFilter implements WebGraphQlInterceptor {

    private final InMemoryPersistedQueryCache persistedQueryCache;

    @Nonnull
    @Override
    public @NotNull Mono<WebGraphQlResponse> intercept(@Nonnull final WebGraphQlRequest request, final Chain chain) {

        checkCacheHit(request);
        logRequest(request);
        return chain.next(request).doOnNext(GraphQLFilter::logResponse);
    }

    private void checkCacheHit(final WebGraphQlRequest request) {
        Map<Object, String> knownQueries = persistedQueryCache.getKnownQueries();
        log.debug("queries in graphql persisted query cache: {}",
                knownQueries.keySet()
                        .stream()
                        .map(Object::toString)
                        .map(key -> truncate(key, 100))
                        .collect(joining(", ")));

        String response = knownQueries.get(request.getId());
        if (response != null) {
            log.info("Fetching query response for request Id: {} from cache: {}", request.getId(),
                    truncate(response, 500));
        }
    }

    private static void logRequest(final WebGraphQlRequest request) {
        log.trace("Request cookies: {}", request.getCookies());
        log.trace("Request attributes: {}", request.getAttributes());
        log.trace("Request document: {}", request.getDocument());
        log.debug("Request executionId: {}", request.getExecutionId());
        log.trace("Request extensions: {}", request.getExtensions());
        log.trace("Request headers: {}", request.getHeaders());
        log.debug("Request id: {}", request.getId());
        log.trace("Request locale: {}", request.getLocale());
        log.debug("Request operationName: {}", request.getOperationName());
        log.debug("Request uri: {}", request.getUri());
        log.debug("Request variables: {}", request.getVariables());
    }

    private static void logResponse(final WebGraphQlResponse response) {
        log.trace("Response responseHeaders: {}", response.getResponseHeaders());
        log.trace("Response extensions: {}", response.getExtensions());
        log.debug("Response errors: {}", response.getErrors());
        log.debug("Response data: {}", truncate(response.getData(), 250));
        log.trace("Response executionInput: {}", response.getExecutionInput());
        log.trace("Response executionResult: {}", response.getExecutionResult());
    }

    @Nonnull
    @Override
    public @NotNull WebGraphQlInterceptor andThen(final @Nonnull WebGraphQlInterceptor nextInterceptor) {
        return WebGraphQlInterceptor.super.andThen(nextInterceptor);
    }

    @Nonnull
    @Override
    public @NotNull Chain apply(final @Nonnull Chain chain) {
        return WebGraphQlInterceptor.super.apply(chain);
    }
}
