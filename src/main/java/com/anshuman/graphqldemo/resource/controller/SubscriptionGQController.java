package com.anshuman.graphqldemo.resource.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SubscriptionGQController {

    private static final AtomicInteger number = new AtomicInteger(0);


    @SubscriptionMapping
    public Publisher<Integer> getNumber() {
        return Flux.fromStream(Stream.generate(() -> {
            sleep(2500);
            return number.incrementAndGet();
        }));
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            log.error("thread interrupted", ex);
            Thread.currentThread().interrupt();
        }
    }

}
