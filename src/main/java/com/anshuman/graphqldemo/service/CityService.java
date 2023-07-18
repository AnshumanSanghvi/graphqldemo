package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.repository.CityRepository;
import com.anshuman.graphqldemo.model.repository.projection.CityProjection;
import com.anshuman.graphqldemo.model.repository.projection.ICityProjection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@Transactional(readOnly = true, transactionManager = "JpaTransactionManager")
public class CityService {

    private final CityRepository cityRepository;
    private final Executor executor;

    public CityService(final CityRepository cityRepository, @Qualifier("APIThreadExecutor") Executor executor) {
        this.cityRepository = cityRepository;
        this.executor = executor;
    }

    @Cacheable(value = "cities", key = "#cityId")
    public CompletableFuture<CityProjection> gqlFindById(Integer cityId) {
        return CompletableFuture
                .supplyAsync(() -> (cityRepository.gqlFindById(cityId)), executor)
                .thenApply(ICityProjection::toPojo);
    }
}
