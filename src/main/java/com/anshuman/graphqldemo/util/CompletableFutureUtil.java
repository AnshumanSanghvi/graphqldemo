package com.anshuman.graphqldemo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
public class CompletableFutureUtil {

    private CompletableFutureUtil() {
        // use statically
    }

    public static <T> T withTryCatchAndTimeLimit(CompletableFuture<T> future, String taskName) {
        try {
            return future.get(30, TimeUnit.SECONDS);
        } catch (InterruptedException e){
            log.error("thread Interrupted: ", e);
            Thread.currentThread().interrupt();
            return null;
        } catch(ExecutionException | TimeoutException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new RuntimeException("Exception encountered in task " + taskName, rootCause);
        }
    }
}
