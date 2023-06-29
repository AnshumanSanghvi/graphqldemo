package com.anshuman.graphqldemo.resource;

import graphql.GraphQLError;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@Component
public class ControllerExceptionHandler {

    @GraphQlExceptionHandler
    public GraphQLError handleGraphQLError(BindException ex) {
        return GraphQLError.newError().errorType(ErrorType.BAD_REQUEST).message("...").build();
    }

    @GraphQlExceptionHandler
    public GraphQLError handleGraphQLError(IllegalAccessError ex) {
        return GraphQLError.newError().errorType(ErrorType.UNAUTHORIZED).message(ex.getMessage()).build();
    }
}
