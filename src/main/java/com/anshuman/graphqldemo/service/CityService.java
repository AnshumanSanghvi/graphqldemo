package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.repository.CityRepository;
import com.anshuman.graphqldemo.model.repository.projection.ICityProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, transactionManager = "JpaTransactionManager")
public class CityService {

    private final CityRepository cityRepository;

    @Cacheable(value = "cities", key = "#cityId")
    public ICityProjection gqlFindById(Integer cityId) {
        return ICityProjection.toPojo(cityRepository.gqlFindById(cityId));
    }
}
