package com.dgs.poc.misc.exceptons.handler;

import com.dgs.poc.misc.exceptons.custom.UserNotFoundException;
import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationExceptionHandler {

    private static final String INTERNAL_SERVER_ERROR = "Internal server error:";

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GraphQLError handleUserNotFoundException(EntityNotFoundException ex, DataFetchingEnvironment dataFetchingEnvironment) {
        return GraphQLError.newError()
                .message(ex.getMessage())
                .path(dataFetchingEnvironment.getExecutionStepInfo().getPath())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GraphQLError handleGeneralException(Exception ex, DataFetchingEnvironment env) {
        return GraphQLError.newError()
                .message(INTERNAL_SERVER_ERROR + ex.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .build();
    }
}

