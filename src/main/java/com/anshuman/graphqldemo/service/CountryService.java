package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.entity.Country;
import com.anshuman.graphqldemo.model.mapper.CountryMapper;
import com.anshuman.graphqldemo.model.repository.CountryRepository;
import com.anshuman.graphqldemo.resource.dto.CountryRecord;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@Transactional(readOnly = true, transactionManager = "JpaTransactionManager")
public class CountryService {

    private final CountryRepository countryRepository;
    private final AtomicInteger countryId = new AtomicInteger(300);
    private final CountryMapper countryMapper;

    public CountryService(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    @Async("APIThreadExecutor")
    @Cacheable(value = "countries", key = "#countryId")
    public CompletableFuture<Country> gqlFindById(Integer countryId) {
        return countryRepository.gqlFindById(countryId);
    }

    @Async("APIThreadExecutor")
    @Cacheable(value = "countries")
    public CompletableFuture<Set<Country>> gqlFindByIds(Set<Integer> countryIds) {
        return countryRepository.gqlFindByIds(countryIds);
    }

    @Transactional(transactionManager = "JpaTransactionManager")
    public CountryRecord createCountry(String name) {
        Country country = new Country(countryId.incrementAndGet(), name,
                Instant.now());
        return countryMapper.toDto(countryRepository.save(country));
    }

    @CacheEvict(value = "countries", key = "#countryId")
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

    @Caching(put = @CachePut(value = "countries", key = "#countryId"),
            evict = @CacheEvict(value = "countries", key = "#countryId"))
    @Transactional(transactionManager = "JpaTransactionManager")
    public CountryRecord updateCountry(@NotNull Integer countryId, @NotNull @NotEmpty String name) {
        return countryRepository
                .findById(countryId)
                .map(country -> {
                    country.setName(name);
                    return country;
                })
                .map(countryRepository::save)
                .map(countryMapper::toDto)
                .orElse(null);
    }
}
