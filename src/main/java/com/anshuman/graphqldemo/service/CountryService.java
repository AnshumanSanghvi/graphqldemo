package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.entity.Country;
import com.anshuman.graphqldemo.model.mapper.CountryMapper;
import com.anshuman.graphqldemo.model.repository.CountryRepository;
import com.anshuman.graphqldemo.resource.dto.CountryRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true, transactionManager = "JpaTransactionManager")
public class CountryService {

    private final CountryRepository countryRepository;
    private final AtomicInteger countryId = new AtomicInteger(300);
    private final CountryMapper countryMapper;

    @Cacheable(value = "countries", key = "#countryId")
    public CountryRecord findById(Integer countryId) {
        return countryRepository.findById(countryId)
                .map(countryMapper::toDto)
                .orElse(null);
    }

    @Transactional(transactionManager = "JpaTransactionManager")
    public CountryRecord createCountry(String name) {
        Country country = new Country(countryId.incrementAndGet(), name,
                Instant.now());
        return countryMapper.toDto(countryRepository.save(country));
    }

    @Transactional(transactionManager = "JpaTransactionManager")
    public boolean deleteCountry(final Integer countryId) {
        try {
            countryRepository.deleteById(countryId);
            return !countryRepository.existsById(countryId);
        } catch (Exception ex) {
            log.error("exception encountered when deleting country with id: {}", countryId, ex);
            return false;
        }
    }
}
