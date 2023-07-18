package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.mapper.CountryMapper;
import com.anshuman.graphqldemo.model.reactive.CountryReactiveRepository;
import com.anshuman.graphqldemo.resource.dto.CountryRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(transactionManager = "ReactiveTransactionManager")
public class CountryReactiveService {

    private final CountryMapper countryMapper;
    private final CountryReactiveRepository countryReactiveRepo;


    public Flux<CountryRecord> getCountries() {
        return countryReactiveRepo
                .findAll()
                .delayElements(Duration.ofSeconds(2))
                .map(countryMapper::toDto);
    }
}
