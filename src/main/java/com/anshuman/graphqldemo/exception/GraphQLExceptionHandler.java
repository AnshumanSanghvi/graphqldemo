package com.anshuman.graphqldemo.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Objects;

@Component
@ControllerAdvice
@Slf4j
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {


    @GraphQlExceptionHandler
    @Override
    protected GraphQLError resolveToSingleError(@Nonnull Throwable ex, @Nonnull DataFetchingEnvironment env) {
        GraphQLError graphQLError;
        Objects.requireNonNull(ex);
        Objects.requireNonNull(env);
        graphQLError = GraphqlErrorBuilder.newError()
                .errorType(ErrorType.INTERNAL_ERROR)
                .message("Internal error encountered")
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();

        Throwable rootCause = ExceptionUtils.getRootCause(Objects.requireNonNull(ex));
        log.error("GraphQLExceptionHandler: Exception encountered: {}, env: {}", rootCause.getMessage(), env, rootCause);
        return graphQLError;
    }
}
