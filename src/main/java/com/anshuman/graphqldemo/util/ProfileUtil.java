package com.anshuman.graphqldemo.util;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class ProfileUtil {

    private ProfileUtil() {
        // use statically
    }

    public static <T> T future(CompletableFuture<T> future, String taskName) {
        Instant startTime = Instant.now();
        T result = CompletableFutureUtil.withTryCatch(future, taskName);
        Instant finishTime = Instant.now();
        log.debug("Task: {} took {} milli-seconds", taskName, Duration.between(startTime, finishTime).toMillis());
        return result;
    }
}
