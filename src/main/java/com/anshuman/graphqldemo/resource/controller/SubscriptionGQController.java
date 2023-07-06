package com.anshuman.graphqldemo.resource.controller;

import com.anshuman.graphqldemo.resource.dto.CountryRecord;
import com.anshuman.graphqldemo.service.CountryReactiveService;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class SubscriptionGQController {

    private static final AtomicInteger number = new AtomicInteger(0);
    private final CountryReactiveService countryService;

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
            throw new RuntimeException(ex);
        }
    }

    @SubscriptionMapping
    public Publisher<CountryRecord> getCountrySubscription() {
        return countryService.getCountries();
    }

}
