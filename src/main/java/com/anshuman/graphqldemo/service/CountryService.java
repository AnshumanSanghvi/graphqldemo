package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.entity.Country;
import com.anshuman.graphqldemo.model.mapper.CountryMapper;
import com.anshuman.graphqldemo.model.repository.CountryRepository;
import com.anshuman.graphqldemo.resource.dto.CountryRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final AtomicInteger countryId = new AtomicInteger(300);
    private final CountryMapper countryMapper;

    public CountryRecord createCountry(String name) {
        Country country = new Country(countryId.incrementAndGet(), name,
                Instant.now());
        return countryMapper.toDto(countryRepository.save(country));
    }
}
