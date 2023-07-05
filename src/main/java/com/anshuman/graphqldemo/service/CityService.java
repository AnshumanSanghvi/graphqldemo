package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.repository.CityRepository;
import com.anshuman.graphqldemo.model.repository.projection.ICityProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CityService {

    private final CityRepository cityRepository;

    public ICityProjection gqlFindById(Integer cityId) {
        return cityRepository.gqlFindById(cityId);
    }
}