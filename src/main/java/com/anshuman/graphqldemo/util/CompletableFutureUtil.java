package com.anshuman.graphqldemo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.anshuman.graphqldemo.util.StringUtil.truncate;

@Slf4j
public class CompletableFutureUtil {

    private CompletableFutureUtil() {
        // use statically
    }

    public static <T> T withTryCatch(CompletableFuture<T> future, String taskName) {
        try {
            withLogging(future, taskName);
            return future.get(30, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new RuntimeException(rootCause);
        }
    }

    public static <T> void withLogging(CompletableFuture<T> future, String taskName) {
        future.thenAccept((result) -> log.debug("resolved task: {} with result: {} on thread: {} ",
                taskName, truncate(result, 1000), Thread.currentThread().getName()));
    }
}
